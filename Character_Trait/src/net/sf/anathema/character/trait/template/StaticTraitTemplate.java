package net.sf.anathema.character.trait.template;


public final class StaticTraitTemplate implements ITraitTemplate {
  
  private final int maxValue;

  public StaticTraitTemplate(int maxValue) {
    this.maxValue = maxValue;
  }
  
  @Override
  public int getMaximalValue(int essenceValue) {
    return maxValue;
  }

  @Override
  public int getMinimalValue() {
    return 0;
  }
}