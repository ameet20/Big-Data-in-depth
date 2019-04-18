package org.apache.spark;

import java.io.Serializable;


public interface SparkExecutorInfo extends Serializable {
  String host();
  int port();
  long cacheSize();
  int numRunningTasks();
  long usedOnHeapStorageMemory();
  long usedOffHeapStorageMemory();
  long totalOnHeapStorageMemory();
  long totalOffHeapStorageMemory();
}
