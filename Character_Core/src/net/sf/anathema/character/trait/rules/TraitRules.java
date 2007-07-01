package net.sf.anathema.character.trait.rules;

public final class TraitRules implements ITraitRules {

  private int minimalValue;

  public void setMiniumalValue(int minimalValue) {
    this.minimalValue = minimalValue;
  }

  @Override
  public int getMinimalValue() {
    return minimalValue;
  }

  @Override
  public int getMaximalValue() {
    return 5;
  }
}