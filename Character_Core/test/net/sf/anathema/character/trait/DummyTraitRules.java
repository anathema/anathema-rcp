package net.sf.anathema.character.trait;

import net.sf.anathema.character.trait.rules.ITraitRules;

public class DummyTraitRules implements ITraitRules {

  private int correctedValue = -1;
  private int maximalValue;

  @Override
  public int getCorrectedValue(int value) {
    if (correctedValue > -1) {
      return correctedValue;
    }
    return value;
  }

  public void setCorrectedValue(int correctedValue) {
    this.correctedValue = correctedValue;
  }

  public void setMaximalValue(int maximalValue) {
    this.maximalValue = maximalValue;
  }
  
  public int getMaximalValue() {
    return maximalValue;
  }
}