package org.apache.hive.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

/**
 * HiveCallableStatement.
 *
 */
public class HiveCallableStatement implements java.sql.CallableStatement {
  private final Connection connection;

  /**
   *
   */
  public HiveCallableStatement(Connection connection) {
    this.connection = connection;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getArray(int)
   */

  public Array getArray(int i) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getArray(java.lang.String)
   */

  public Array getArray(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getBigDecimal(int)
   */

  public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getBigDecimal(java.lang.String)
   */

  public BigDecimal getBigDecimal(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getBigDecimal(int, int)
   */

  public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getBlob(int)
   */

  public Blob getBlob(int i) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getBlob(java.lang.String)
   */

  public Blob getBlob(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getBoolean(int)
   */

  public boolean getBoolean(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getBoolean(java.lang.String)
   */

  public boolean getBoolean(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getByte(int)
   */

  public byte getByte(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getByte(java.lang.String)
   */

  public byte getByte(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getBytes(int)
   */

  public byte[] getBytes(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getBytes(java.lang.String)
   */

  public byte[] getBytes(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getCharacterStream(int)
   */

  public Reader getCharacterStream(int arg0) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getCharacterStream(java.lang.String)
   */

  public Reader getCharacterStream(String arg0) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getClob(int)
   */

  public Clob getClob(int i) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getClob(java.lang.String)
   */

  public Clob getClob(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getDate(int)
   */

  public Date getDate(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getDate(java.lang.String)
   */

  public Date getDate(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getDate(int, java.util.Calendar)
   */

  public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getDate(java.lang.String,
   * java.util.Calendar)
   */

  public Date getDate(String parameterName, Calendar cal) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getDouble(int)
   */

  public double getDouble(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getDouble(java.lang.String)
   */

  public double getDouble(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getFloat(int)
   */

  public float getFloat(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getFloat(java.lang.String)
   */

  public float getFloat(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getInt(int)
   */

  public int getInt(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getInt(java.lang.String)
   */

  public int getInt(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getLong(int)
   */

  public long getLong(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getLong(java.lang.String)
   */

  public long getLong(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getNCharacterStream(int)
   */

  public Reader getNCharacterStream(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getNCharacterStream(java.lang.String)
   */

  public Reader getNCharacterStream(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getNClob(int)
   */

  public NClob getNClob(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getNClob(java.lang.String)
   */

  public NClob getNClob(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getNString(int)
   */

  public String getNString(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getNString(java.lang.String)
   */

  public String getNString(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getObject(int)
   */

  public Object getObject(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getObject(java.lang.String)
   */

  public Object getObject(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
    // TODO JDK 1.7
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
    // TODO JDK 1.7
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getObject(int, java.util.Map)
   */

  public Object getObject(int i, Map<String, Class<?>> map) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getObject(java.lang.String, java.util.Map)
   */

  public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getRef(int)
   */

  public Ref getRef(int i) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getRef(java.lang.String)
   */

  public Ref getRef(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getRowId(int)
   */

  public RowId getRowId(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getRowId(java.lang.String)
   */

  public RowId getRowId(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getSQLXML(int)
   */

  public SQLXML getSQLXML(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getSQLXML(java.lang.String)
   */

  public SQLXML getSQLXML(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getShort(int)
   */

  public short getShort(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getShort(java.lang.String)
   */

  public short getShort(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getString(int)
   */

  public String getString(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getString(java.lang.String)
   */

  public String getString(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getTime(int)
   */

  public Time getTime(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getTime(java.lang.String)
   */

  public Time getTime(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getTime(int, java.util.Calendar)
   */

  public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getTime(java.lang.String,
   * java.util.Calendar)
   */

  public Time getTime(String parameterName, Calendar cal) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getTimestamp(int)
   */

  public Timestamp getTimestamp(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getTimestamp(java.lang.String)
   */

  public Timestamp getTimestamp(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getTimestamp(int, java.util.Calendar)
   */

  public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getTimestamp(java.lang.String,
   * java.util.Calendar)
   */

  public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getURL(int)
   */

  public URL getURL(int parameterIndex) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#getURL(java.lang.String)
   */

  public URL getURL(String parameterName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#registerOutParameter(int, int)
   */

  public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#registerOutParameter(java.lang.String, int)
   */

  public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#registerOutParameter(int, int, int)
   */

  public void registerOutParameter(int parameterIndex, int sqlType, int scale)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#registerOutParameter(int, int,
   * java.lang.String)
   */

  public void registerOutParameter(int paramIndex, int sqlType, String typeName)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#registerOutParameter(java.lang.String, int,
   * int)
   */

  public void registerOutParameter(String parameterName, int sqlType, int scale)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#registerOutParameter(java.lang.String, int,
   * java.lang.String)
   */

  public void registerOutParameter(String parameterName, int sqlType,
      String typeName) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setAsciiStream(java.lang.String,
   * java.io.InputStream)
   */

  public void setAsciiStream(String parameterName, InputStream x)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setAsciiStream(java.lang.String,
   * java.io.InputStream, int)
   */

  public void setAsciiStream(String parameterName, InputStream x, int length)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setAsciiStream(java.lang.String,
   * java.io.InputStream, long)
   */

  public void setAsciiStream(String parameterName, InputStream x, long length)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setBigDecimal(java.lang.String,
   * java.math.BigDecimal)
   */

  public void setBigDecimal(String parameterName, BigDecimal x)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setBinaryStream(java.lang.String,
   * java.io.InputStream)
   */

  public void setBinaryStream(String parameterName, InputStream x)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setBinaryStream(java.lang.String,
   * java.io.InputStream, int)
   */

  public void setBinaryStream(String parameterName, InputStream x, int length)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setBinaryStream(java.lang.String,
   * java.io.InputStream, long)
   */

  public void setBinaryStream(String parameterName, InputStream x, long length)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setBlob(java.lang.String, java.sql.Blob)
   */

  public void setBlob(String parameterName, Blob x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setBlob(java.lang.String,
   * java.io.InputStream)
   */

  public void setBlob(String parameterName, InputStream inputStream)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setBlob(java.lang.String,
   * java.io.InputStream, long)
   */

  public void setBlob(String parameterName, InputStream inputStream, long length)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setBoolean(java.lang.String, boolean)
   */

  public void setBoolean(String parameterName, boolean x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setByte(java.lang.String, byte)
   */

  public void setByte(String parameterName, byte x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setBytes(java.lang.String, byte[])
   */

  public void setBytes(String parameterName, byte[] x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setCharacterStream(java.lang.String,
   * java.io.Reader)
   */

  public void setCharacterStream(String parameterName, Reader reader)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setCharacterStream(java.lang.String,
   * java.io.Reader, int)
   */

  public void setCharacterStream(String parameterName, Reader reader, int length)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setCharacterStream(java.lang.String,
   * java.io.Reader, long)
   */

  public void setCharacterStream(String parameterName, Reader reader,
      long length) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setClob(java.lang.String, java.sql.Clob)
   */

  public void setClob(String parameterName, Clob x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setClob(java.lang.String, java.io.Reader)
   */

  public void setClob(String parameterName, Reader reader) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setClob(java.lang.String, java.io.Reader,
   * long)
   */

  public void setClob(String parameterName, Reader reader, long length)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setDate(java.lang.String, java.sql.Date)
   */

  public void setDate(String parameterName, Date x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setDate(java.lang.String, java.sql.Date,
   * java.util.Calendar)
   */

  public void setDate(String parameterName, Date x, Calendar cal)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setDouble(java.lang.String, double)
   */

  public void setDouble(String parameterName, double x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setFloat(java.lang.String, float)
   */

  public void setFloat(String parameterName, float x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setInt(java.lang.String, int)
   */

  public void setInt(String parameterName, int x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setLong(java.lang.String, long)
   */

  public void setLong(String parameterName, long x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setNCharacterStream(java.lang.String,
   * java.io.Reader)
   */

  public void setNCharacterStream(String parameterName, Reader value)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setNCharacterStream(java.lang.String,
   * java.io.Reader, long)
   */

  public void setNCharacterStream(String parameterName, Reader value,
      long length) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setNClob(java.lang.String, java.sql.NClob)
   */

  public void setNClob(String parameterName, NClob value) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setNClob(java.lang.String, java.io.Reader)
   */

  public void setNClob(String parameterName, Reader reader) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setNClob(java.lang.String, java.io.Reader,
   * long)
   */

  public void setNClob(String parameterName, Reader reader, long length)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setNString(java.lang.String,
   * java.lang.String)
   */

  public void setNString(String parameterName, String value)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setNull(java.lang.String, int)
   */

  public void setNull(String parameterName, int sqlType) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setNull(java.lang.String, int,
   * java.lang.String)
   */

  public void setNull(String parameterName, int sqlType, String typeName)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setObject(java.lang.String,
   * java.lang.Object)
   */

  public void setObject(String parameterName, Object x) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setObject(java.lang.String,
   * java.lang.Object, int)
   */

  public void setObject(String parameterName, Object x, int targetSqlType)
      throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.CallableStatement#setObject(java.lang.String,
   * java.lang.Object, int, int)
   */

  public void setObject(String parameterName, Object x, int targetSqlType,
      int scale) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }
  /*
   * (non-Javadoc)
   *
   * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
   */

  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

  /*
   * (non-Javadoc)
   *
   * @see java.sql.Wrapper#unwrap(java.lang.Class)
   */

  public <T> T unwrap(Class<T> iface) throws SQLException {
    // TODO Auto-generated method stub
    throw new SQLFeatureNotSupportedException("Method not supported");
  }

}
