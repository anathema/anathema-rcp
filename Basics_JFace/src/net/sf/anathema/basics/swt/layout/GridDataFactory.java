package net.sf.anathema.basics.swt.layout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;

public class GridDataFactory {

  public static GridData createIndentData(int indent) {
    GridData data = new GridData();
    data.horizontalIndent = indent;
    return data;
  }

  public static GridData createHorizontalSpanData(int span) {
    GridData data = new GridData();
    data.horizontalSpan = span;
    return data;
  }

  public static GridData createHorizontalFill() {
    return new GridData(SWT.FILL, SWT.CENTER, true, false);
  }

  public static GridData createFillBoth() {
    return new GridData(SWT.FILL, SWT.FILL, true, true);
  }
  
  public static GridData createRightAlign() {
    return new GridData(SWT.END, SWT.CENTER, false, false);
  }
}