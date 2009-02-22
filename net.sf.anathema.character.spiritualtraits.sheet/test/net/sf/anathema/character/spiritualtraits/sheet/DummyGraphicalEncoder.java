package net.sf.anathema.character.spiritualtraits.sheet;

import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.character.sheet.content.IGraphicalEncoder;
import net.sf.anathema.character.sheet.elements.Position;

public class DummyGraphicalEncoder implements IGraphicalEncoder {

  private final Set<Position> filledCirclePositions = new HashSet<Position>();

  @Override
  public void drawCircle(Position lowerLeft, int size, boolean isFilled) {
    if (isFilled) {
      filledCirclePositions.add(lowerLeft);
    }
  }

  public int getDistinctFilledCircleCount() {
    return filledCirclePositions.size();
  }

  @Override
  public void drawComment(String text, Position position, int alignment) {
    // TODO Auto-generated method stub

  }

  @Override
  public void drawLabelledContent(String label, String content, Position position, float width) {
    // TODO Auto-generated method stub

  }

  @Override
  public void drawMissingTextLine(Position position, float length) {
    // TODO Auto-generated method stub

  }

  @Override
  public void drawRectangle(Position lowerLeft, int size, boolean filled) {
    // TODO Auto-generated method stub

  }

  @Override
  public void drawText(String text, Position position, int alignment) {
    // TODO Auto-generated method stub

  }

  @Override
  public void drawVerticalText(String text, Position position, int alignment) {
    // TODO Auto-generated method stub

  }

  @Override
  public int getCommentTextWidth(String text) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getDefaultTextWidth(String text) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void setCommentFont() {
    // TODO Auto-generated method stub

  }

  @Override
  public void setDefaultFont() {
    // TODO Auto-generated method stub

  }

  @Override
  public void setFillColorBlack() {
    // TODO Auto-generated method stub

  }

}
