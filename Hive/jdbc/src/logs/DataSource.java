package org.apache.hive.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * HiveDataSource.
 *
 */
public class HiveDataSource implements DataSource {

  /**
   *
   */
  public HiveDataSource() {
    // TODO Auto-generated constructor stub
  }

  /*
   * (non-Javadoc)
   *
   * @see javax.sql.DataSource#getConnection()
   */

  @Override
  public Connection getConnection() throws SQLException {
    return getConnection("", "");
  }

  /*
   * (non-Javadoc)
   *
   * @see javax.sql.DataSource#getConnection(java.lang.String, java.lang.String)
   */

  @Override
  public Connection getConnection(String username, String password)
      throws SQLException {
    try {
      return new HiveConnection("", null);
    } catch (Exception ex) {
      throw new SQLException("Error in getting HiveConnection",ex);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see javax.sql.CommonDataSource#getLogWriter()
   */

  @Override
  public PrintWriter getLogWriter() throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see javax.sql.CommonDataSource#getLoginTimeout()
   */

  @Override
  public int getLoginTimeout() throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  public Logger getParentLogger() throws SQLFeatureNotSupportedException {
    // JDK 1.7
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see javax.sql.CommonDataSource#setLogWriter(java.io.PrintWriter)
   */

  @Override
  public void setLogWriter(PrintWriter arg0) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see javax.sql.CommonDataSource#setLoginTimeout(int)
   */

  @Override
  public void setLoginTimeout(int arg0) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
   */

  @Override
  public boolean isWrapperFor(Class<?> arg0) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Wrapper#unwrap(java.lang.Class)
   */

  @Override
  public <T> T unwrap(Class<T> arg0) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

}
