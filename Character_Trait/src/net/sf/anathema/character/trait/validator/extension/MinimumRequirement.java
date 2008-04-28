package net.sf.anathema.character.trait.validator.extension;

import net.disy.commons.core.predicate.ICheck;
import net.sf.anathema.character.trait.IBasicTrait;

public class MinimumRequirement implements ICheck {

  private final IBasicTrait trait;
  private final int minimumValue;

  public MinimumRequirement(IBasicTrait trait, int minimumValue) {
    this.trait = trait;
    this.minimumValue = minimumValue;
  }

  @Override
  public boolean isConfirmed() {
    return trait.getCreationModel().getValue() >= minimumValue;
  }

  public int getMinimalValue() {
    return minimumValue;
  }
}