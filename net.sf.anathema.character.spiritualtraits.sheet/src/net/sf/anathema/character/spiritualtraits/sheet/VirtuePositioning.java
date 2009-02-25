package net.sf.anathema.character.spiritualtraits.sheet;

import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;

public class VirtuePositioning {

  private final Bounds bounds;

  public VirtuePositioning(final Bounds bounds) {
    this.bounds = bounds;
  }

  private float getPadding() {
    return bounds.width / 8;
  }

  private float getLeftCellX() {
    return bounds.x + getPadding() / 2;
  }

  private float getRightCellX() {
    return (int) (bounds.x + getCellWidth() + getPadding() * 1.5);
  }

  private float getUpperCellY() {
    return (int) bounds.getMaxY();
  }

  private float getLowerCellY() {
    return (int) bounds.getCenterY();
  }

  public Position getCompassionPosition() {
    return new Position(getLeftCellX(), getUpperCellY());
  }

  public Position getTemperancePosition() {
    return new Position(getRightCellX(), getUpperCellY());
  }

  public Position getConvictionPosition() {
    return new Position(getLeftCellX(), getLowerCellY());
  }

  public Position getValorPosition() {
    return new Position(getRightCellX(), getLowerCellY());
  }

  public float getCellWidth() {
    return (bounds.width - 2 * getPadding()) / 2;
  }
}