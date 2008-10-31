package net.sf.anathema.character.core.traitview;

public class IntDisplayArea {
  public static final int GROUP_SIZE = 5;
  public static final int HORIZONTAL_INDENT = 2;
  private final int imageHeight;
  private final int slotWidth;
  private final int whitespaceSlotWidth;
  private int maxValue;

  public IntDisplayArea(final int imageHeight, final int imageWidth, int maxValue) {
    this.imageHeight = imageHeight;
    this.maxValue = maxValue;
    this.slotWidth = imageWidth + 2;
    this.whitespaceSlotWidth = slotWidth / 2;
  }

  public int getImageHeight() {
    return imageHeight;
  }

  public int getXPosition(int imageIndex) {
    return HORIZONTAL_INDENT + imageIndex * slotWidth + getWhitespaceWidth(imageIndex);
  }

  private int getWhitespaceWidth(int index) {
    return (index / GROUP_SIZE) * whitespaceSlotWidth;
  }

  public int getIndexForPosition(int x) {
    if (x < slotWidth / 3) {
      return 0;
    }
    for (int imageIndex = 0; imageIndex < maxValue; imageIndex++) {
      if (x < getXPosition(imageIndex)) {
        return imageIndex;
      }
    }
    return maxValue;
  }
  
  public void setMaxValue(int maxValue) {
    this.maxValue = maxValue;
  }
  
  public int getMaxValue() {
    return maxValue;
  }

  public int getPreferredWidth() {
    int preferredWidth = getXPosition(getMaxValue());
    if (getMaxValue() % IntDisplayArea.GROUP_SIZE == 0) {
      preferredWidth -= whitespaceSlotWidth;
    } 
    return preferredWidth;
  }

  public int getPreferredHeight() {
    return getImageHeight() + 2;
  }
}