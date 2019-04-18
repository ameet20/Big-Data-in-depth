package org.apache.spark;

import java.io.Serializable;


public interface SparkStageInfo extends Serializable {
  int stageId();
  int currentAttemptId();
  long submissionTime();
  String name();
  int numTasks();
  int numActiveTasks();
  int numCompletedTasks();
  int numFailedTasks();
}
