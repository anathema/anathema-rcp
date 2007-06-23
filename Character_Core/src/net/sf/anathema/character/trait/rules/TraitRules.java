package net.sf.anathema.character.trait.rules;

public final class TraitRules implements ITraitRules {

  private int minimalValue;

  public void setMiniumalValue(int minimalValue) {
    this.minimalValue = minimalValue;
  }

  public int getCorrectedValue(int value) {
    if (value < minimalValue) {
      return minimalValue;
    }
    return value;
  }

  @Override
  public int getMaximalValue() {
    return 5;
  }
}