package net.sf.anathema.basics.swt.layout;

import org.eclipse.swt.layout.GridData;

public class GridDataFactory {

  public static GridData createIndentData(int indent) {
    GridData data = new GridData();
    data.horizontalIndent = indent;
    return data;
  }

  public static GridData createHorizontalSpanData(int span) {
    GridData groupData = new GridData();
    groupData.horizontalSpan = span;
    return groupData;
  }
}