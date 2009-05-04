package net.sf.anathema.character.sheet.anima.util;


public class ColumnDescriptor {

  private final String headerTitle;
  private final float widthPart;

  public ColumnDescriptor(float widthPart, String headerTitle) {
    this.widthPart = widthPart;
    this.headerTitle = headerTitle;
  }

  public String getHeaderTitle() {
    return headerTitle;
  }

  public float getWidthPart() {
    return widthPart;
  }
}