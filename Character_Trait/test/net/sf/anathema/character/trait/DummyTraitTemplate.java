package net.sf.anathema.character.trait;

import net.sf.anathema.character.trait.template.ITraitTemplate;

public class DummyTraitTemplate implements ITraitTemplate {

  private int maximalValue = 5;
  private int minimalValue = 0;

  public void setMaximalValue(int maximalValue) {
    this.maximalValue = maximalValue;
  }
  
  public void setMinimalValue(int minimalValue) {
    this.minimalValue = minimalValue;
  }
  
  @Override
  public int getMaximalValue(int essenceValue) {
    return maximalValue;
  }

  @Override
  public int getMinimalValue() {
    return minimalValue;
  }
}