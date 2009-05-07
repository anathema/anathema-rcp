package net.sf.anathema.character.backgrounds.sheet;

import static net.sf.anathema.character.sheet.page.IVoidStateFormatConstants.*;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;

public class LineIterator {

  private final Bounds bounds;
  private int currentLine = 0;

  public LineIterator(Bounds bounds) {
    this.bounds = bounds;
  }

  public boolean hasNext() {
    int nextLine = currentLine + 1;
    return getLineBaseY(nextLine) > bounds.getMinY();
  }

  public Position nextLineStart() {
    return new Position(bounds.x, getLineBaseY(++currentLine));
  }

  private float getLineBaseY(int line) {
    return bounds.getMaxY() - line * LINE_HEIGHT;
  }

  public float getLineWidth() {
    return bounds.width;
  }
}