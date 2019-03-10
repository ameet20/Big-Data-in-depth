package org.apache.beam.sdk.schemas;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.beam.sdk.annotations.Experimental;
import org.apache.beam.sdk.annotations.Experimental.Kind;
import org.apache.beam.sdk.schemas.annotations.SchemaFieldName;
import org.apache.beam.sdk.schemas.annotations.SchemaIgnore;
import org.apache.beam.sdk.schemas.utils.FieldValueTypeSupplier;
import org.apache.beam.sdk.schemas.utils.POJOUtils;
import org.apache.beam.sdk.schemas.utils.ReflectUtils;
import org.apache.beam.sdk.values.TypeDescriptor;
import org.apache.beam.vendor.guava.v20_0.com.google.common.annotations.VisibleForTesting;
@Experimental(Kind.SCHEMAS)
public class JavaFieldSchema extends GetterBasedSchemaProvider {
  /** {@link FieldValueTypeSupplier} that's based on public fields. */
  @VisibleForTesting
  public static class JavaFieldTypeSupplier implements FieldValueTypeSupplier {
    public static final JavaFieldTypeSupplier INSTANCE = new JavaFieldTypeSupplier();

    @Override
    public List<FieldValueTypeInformation> get(Class<?> clazz) {
      List<FieldValueTypeInformation> types =
          ReflectUtils.getFields(clazz).stream()
              .filter(f -> !f.isAnnotationPresent(SchemaIgnore.class))
              .map(FieldValueTypeInformation::forField)
              .map(
                  t -> {
                    SchemaFieldName fieldName = t.getField().getAnnotation(SchemaFieldName.class);
                    return (fieldName != null) ? t.withName(fieldName.value()) : t;
                  })
              .collect(Collectors.toList());

      // If there are no creators registered, then make sure none of the schema fields are final,
      // as we (currently) have no way of creating classes in this case.
      if (ReflectUtils.getAnnotatedCreateMethod(clazz) == null
          && ReflectUtils.getAnnotatedConstructor(clazz) == null) {
        Optional<Field> finalField =
            types.stream()
                .map(FieldValueTypeInformation::getField)
                .filter(f -> Modifier.isFinal(f.getModifiers()))
                .findAny();
        if (finalField.isPresent()) {
          throw new IllegalArgumentException(
              "Class "
                  + clazz
                  + " has final fields and no "
                  + "registered creator. Cannot use as schema, as we don't know how to create this "
                  + "object automatically");
        }
      }
      return types;
    }
  }

  @Override
  public <T> Schema schemaFor(TypeDescriptor<T> typeDescriptor) {
    return POJOUtils.schemaFromPojoClass(
        typeDescriptor.getRawType(), JavaFieldTypeSupplier.INSTANCE);
  }

  @Override
  public FieldValueGetterFactory fieldValueGetterFactory() {
    return (Class<?> targetClass, Schema schema) ->
        POJOUtils.getGetters(targetClass, schema, JavaFieldTypeSupplier.INSTANCE);
  }

  @Override
  public FieldValueTypeInformationFactory fieldValueTypeInformationFactory() {
    return (Class<?> targetClass, Schema schema) ->
        POJOUtils.getFieldTypes(targetClass, schema, JavaFieldTypeSupplier.INSTANCE);
  }

  @Override
  UserTypeCreatorFactory schemaTypeCreatorFactory() {
    return (Class<?> targetClass, Schema schema) -> {
      // If a static method is marked with @SchemaCreate, use that.
      Method annotated = ReflectUtils.getAnnotatedCreateMethod(targetClass);
      if (annotated != null) {
        return POJOUtils.getStaticCreator(
            targetClass, annotated, schema, JavaFieldTypeSupplier.INSTANCE);
      }

      // If a Constructor was tagged with @SchemaCreate, invoke that constructor.
      Constructor<?> constructor = ReflectUtils.getAnnotatedConstructor(targetClass);
      if (constructor != null) {
        return POJOUtils.getConstructorCreator(
            targetClass, constructor, schema, JavaFieldTypeSupplier.INSTANCE);
      }

      return POJOUtils.getSetFieldCreator(targetClass, schema, JavaFieldTypeSupplier.INSTANCE);
    };
  }
}
