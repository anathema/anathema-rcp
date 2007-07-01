package net.sf.anathema.character.trait;

import net.sf.anathema.character.trait.rules.ITraitRules;

public class DummyTraitRules implements ITraitRules {

  private int maximalValue;
  private int minimalValue;

  public void setMaximalValue(int maximalValue) {
    this.maximalValue = maximalValue;
  }
  
  public void setMinimalValue(int minimalValue) {
    this.minimalValue = minimalValue;
  }
  
  public int getMaximalValue() {
    return maximalValue;
  }

  @Override
  public int getMinimalValue() {
    return minimalValue;
  }
}