package net.sf.anathema.character.spiritualtraits.sheet;

import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.character.sheet.content.IGraphicalEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Box;
import net.sf.anathema.character.sheet.elements.Position;

@SuppressWarnings("nls")
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
    throw new UnsupportedOperationException("Dummy");
  }

  @Override
  public void drawLabelledContent(String label, String content, Position position, float width) {
    throw new UnsupportedOperationException("Dummy");
  }

  @Override
  public void drawMissingTextLine(Position position, float length) {
    throw new UnsupportedOperationException("Dummy");
  }

  @Override
  public void drawSquare(Position lowerLeft, int size, boolean filled) {
    throw new UnsupportedOperationException("Dummy");
  }

  @Override
  public void drawText(String text, Position position, int alignment) {
    throw new UnsupportedOperationException("Dummy");
  }

  @Override
  public void drawVerticalText(String text, Position position, int alignment) {
    throw new UnsupportedOperationException("Dummy");
  }

  @Override
  public int getCommentTextWidth(String text) {
    throw new UnsupportedOperationException("Dummy");
  }

  @Override
  public int getDefaultTextWidth(String text) {
    throw new UnsupportedOperationException("Dummy");
  }

  @Override
  public void setCommentFont() {
    throw new UnsupportedOperationException("Dummy");
  }

  @Override
  public void setDefaultFont() {
    throw new UnsupportedOperationException("Dummy");
  }

  @Override
  public void setFillColorBlack() {
    throw new UnsupportedOperationException("Dummy");
  }

  @Override
  public Box createBox(Bounds bound) {
    throw new UnsupportedOperationException("Dummy");
  }
}