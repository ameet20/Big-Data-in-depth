package org.apache.beam.sdk.testing;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import org.hamcrest.Matcher;

/**
 * A {@link Matcher} that is also {@link Serializable}.
 *
 * <p>Such matchers can be used with {@link PAssert}, which builds pipelines such that these
 * matchers may be serialized and executed remotely.
 *
 * <p>To create a {@code SerializableMatcher}, extend {@link org.hamcrest.BaseMatcher} and also
 * implement this interface.
 *
 * @param <T> The type of value matched.
 */
@JsonSerialize(using = MatcherSerializer.class)
@JsonDeserialize(using = MatcherDeserializer.class)
public interface SerializableMatcher<T> extends Matcher<T>, Serializable {}
