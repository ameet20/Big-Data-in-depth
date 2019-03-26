package org.apache.sqoop.tool;

/**
 * Describes a SqoopTool.
 * This class should be final
 */
public class ToolDesc {
  private final String toolName;
  private final Class<? extends SqoopTool> toolClass;
  private final String description;


  /**
   * Main c'tor; sets all fields that describe a SqoopTool.
   */
  public ToolDesc(String name, Class<? extends SqoopTool> cls, String desc) {
    this.toolName = name;
    this.toolClass = cls;
    this.description = desc;
  }

  /**
   * @return the name used to invoke the tool (e.g., 'sqoop &lt;foo&gt;')
   */
  public String getName() {
    return toolName;
  }

  /**
   * @return a human-readable description of what the tool does.
   */
  public String getDesc() {
    return description;
  }

  /**
   * @return the class that implements SqoopTool.
   */
  public Class<? extends SqoopTool> getToolClass() {
    return toolClass;
  }

}
