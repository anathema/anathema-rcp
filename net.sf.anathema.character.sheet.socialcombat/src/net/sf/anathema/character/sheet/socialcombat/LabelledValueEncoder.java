package net.sf.anathema.character.sheet.socialcombat;

import net.sf.anathema.character.sheet.content.IGraphicalEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.sheet.page.IVoidStateFormatConstants;

import com.lowagie.text.Element;

public class LabelledValueEncoder {

  private final static float BOX_HEIGHT = IVoidStateFormatConstants.LINE_HEIGHT - 2;
  private final static float BOX_WIDTH = 12;

  private final int columnCount;
  private final Position position;
  private final float width;
  private final float baseLine;
  private final float commentLine;
  private final float padding;
  private boolean commentPresent = false;

  public LabelledValueEncoder(int columnCount, Position position, float width, int padding) {
    this.columnCount = columnCount;
    this.position = position;
    this.width = width;
    this.baseLine = position.y - BOX_HEIGHT - padding;
    this.commentLine = baseLine - IVoidStateFormatConstants.COMMENT_FONT_SIZE - padding + 1;
    this.padding = padding;
  }

  private float getRightColumnX(int index) {
    return position.x + width / columnCount * (index + 1);
  }

  public void addComment(IGraphicalEncoder graphicalEncoder, String text, int column) {
    float rightX = getRightColumnX(column);
    commentPresent = true;
    graphicalEncoder.drawComment(text, new Position(rightX, commentLine), Element.ALIGN_RIGHT);
  }

  public void addLabelledValue(IGraphicalEncoder graphicalEncoder, int column, String text, int... values) {
    float rightX = getRightColumnX(column);
    float allBoxesWidth = BOX_WIDTH * values.length + (values.length - 1) * padding;
    Position textPosition = new Position(rightX - allBoxesWidth - padding, baseLine);
    graphicalEncoder.drawText(text, textPosition, Element.ALIGN_RIGHT);
    for (int index = 0; index < values.length; index++) {
      float boxX = rightX - allBoxesWidth + (BOX_WIDTH + padding) * index;
      Bounds boxBounds = new Bounds(boxX, textPosition.y - 2, BOX_WIDTH, BOX_HEIGHT);
      graphicalEncoder.createBox(boxBounds).encode(0.75f);
      Position valuePosition = new Position(boxBounds.getCenterX(), textPosition.getY());
      graphicalEncoder.drawText(String.valueOf(values[index]), valuePosition, Element.ALIGN_CENTER);
    }
  }

  public float getHeight() {
    float textPosition = commentPresent ? commentLine : baseLine;
    return position.y - textPosition + 1;
  }
}