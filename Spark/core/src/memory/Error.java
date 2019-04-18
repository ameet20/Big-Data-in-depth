package org.apache.spark.memory;

import org.apache.spark.annotation.Private;

/**
 * This exception is thrown when a task can not acquire memory from the Memory manager.
 * Instead of throwing {@link OutOfMemoryError}, which kills the executor,
 * we should use throw this exception, which just kills the current task.
 */
@Private
public final class SparkOutOfMemoryError extends OutOfMemoryError {

    public SparkOutOfMemoryError(String s) {
        super(s);
    }

    public SparkOutOfMemoryError(OutOfMemoryError e) {
        super(e.getMessage());
    }
}
