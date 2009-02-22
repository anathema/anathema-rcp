package net.sf.anathema.character.sheet.content;

import net.sf.anathema.character.sheet.elements.Position;

public interface IGraphicalEncoder {

  public void setCommentFont();

  public void setDefaultFont();

  public int getDefaultTextWidth(String text);

  public int getCommentTextWidth(String text);

  public void setFillColorBlack();

  public void drawMissingTextLine(Position position, float length);

  public void drawComment(String text, Position position, int alignment);

  public void drawText(String text, Position position, int alignment);

  public void drawVerticalText(String text, Position position, int alignment);

  public void drawLabelledContent(String label, String content, Position position, float width);

  public void drawRectangle(Position lowerLeft, int size, boolean filled);

  public void drawCircle(Position lowerLeft, int size, boolean isFilled);
}