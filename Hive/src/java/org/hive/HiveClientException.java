package org.apache.hive.jdbc;

public class ZooKeeperHiveClientException extends Exception {

  private static final long serialVersionUID = 0;

  /**
   * @param cause (original exception)
   */
  public ZooKeeperHiveClientException(Throwable cause) {
    super(cause);
  }

  /**
   * @param msg (exception message)
   */
  public ZooKeeperHiveClientException(String msg) {
    super(msg);
  }

  /**
   * @param msg (exception message)
   * @param cause (original exception)
   */
  public ZooKeeperHiveClientException(String msg, Throwable cause) {
    super(msg, cause);
  }

}
