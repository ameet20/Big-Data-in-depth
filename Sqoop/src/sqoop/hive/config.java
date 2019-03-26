package org.apache.sqoop.hive;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;

import java.io.IOException;
import java.util.Map;

public class HiveConfig {

  public static final Log LOG = LogFactory.getLog(HiveConfig.class.getName());

  public static final String HIVE_CONF_CLASS = "org.apache.hadoop.hive.conf.HiveConf";

  public static final String HIVE_SASL_ENABLED = "hive.metastore.sasl.enabled";

  /**
   * Dynamically create hive configuration object.
   * @param conf
   * @return
   * @throws IOException if instantiate HiveConf failed.
   */
  public static Configuration getHiveConf(Configuration conf) throws IOException {
    try {
      Class HiveConfClass = Class.forName(HIVE_CONF_CLASS);
      return ((Configuration)(HiveConfClass.getConstructor(Configuration.class, Class.class)
          .newInstance(conf, Configuration.class)));
    } catch (ClassNotFoundException ex) {
      LOG.error("Could not load " + HIVE_CONF_CLASS
          + ". Make sure HIVE_CONF_DIR is set correctly.");
      throw new IOException(ex);
    } catch (Exception ex) {
      LOG.error("Could not instantiate HiveConf instance.", ex);
      throw new IOException(ex);
    }
  }

  /**
   * Add hive conf to configuration object without overriding already set properties.
   * @param hiveConf
   * @param conf
   */
  public static void addHiveConfigs(Configuration hiveConf, Configuration conf) {
    for (Map.Entry<String, String> item : hiveConf) {
      conf.setIfUnset(item.getKey(), item.getValue());
    }
  }
}
