package org.apache.spark;

import java.io.Serializable;


public interface SparkJobInfo extends Serializable {
  int jobId();
  int[] stageIds();
  JobExecutionStatus status();
}
