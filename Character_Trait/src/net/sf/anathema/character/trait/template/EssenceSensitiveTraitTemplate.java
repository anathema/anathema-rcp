package net.sf.anathema.character.trait.template;

public final class EssenceSensitiveTraitTemplate implements ITraitTemplate {

  private int minimalValue;

  public void setMiniumalValue(int minimalValue) {
    this.minimalValue = minimalValue;
  }

  @Override
  public int getMinimalValue() {
    return minimalValue;
  }

  @Override
  public int getMaximalValue(int essenceValue) {
    return Math.max(5, essenceValue);
  }
}