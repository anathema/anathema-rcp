package net.sf.anathema.character.trait.interactive.validator;

import net.sf.anathema.character.trait.display.IDisplayTrait;

public class RespectValueMaximum implements IValidator {

  private final IDisplayTrait displayTrait;

  public RespectValueMaximum(IDisplayTrait displayTrait) {
    this.displayTrait = displayTrait;
  }

  @Override
  public int getValidValue(int value) {
    return Math.min(value, displayTrait.getMaximalValue());
  }
}