package net.sf.anathema.character.trait.rules;

public final class TraitTemplate implements ITraitTemplate {

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

  @Override
  public boolean isFavorable() {
    return false;
  }
}