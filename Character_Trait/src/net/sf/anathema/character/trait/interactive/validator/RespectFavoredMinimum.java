package net.sf.anathema.character.trait.interactive.validator;

import net.sf.anathema.character.trait.display.IDisplayFavorization;

public class RespectFavoredMinimum implements IValidator {

  private final IDisplayFavorization favorization;

  public RespectFavoredMinimum(IDisplayFavorization favorization) {
    this.favorization = favorization;
  }

  @Override
  public int getValidValue(int value) {
    return Math.max(favorization.isFavored() ? 1 : 0, value);
  }
}