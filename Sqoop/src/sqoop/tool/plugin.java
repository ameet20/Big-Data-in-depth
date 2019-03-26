package org.apache.sqoop.tool;

import java.util.List;

/**
 * Abstract base class that defines the ToolPlugin API; additional SqoopTool
 * implementations may be registered with the system via ToolPlugin classes.
 */
public abstract class ToolPlugin {
  /**
   * Describes the tools made available by this plugin.
   * @return a list of ToolDesc objects containing the tool name, class,
   * and description.
   */
  public abstract List<ToolDesc> getTools();
}
