package net.sf.anathema.character.trait;

import net.sf.anathema.character.trait.template.ITraitTemplate;

public class DummyTraitTemplate implements ITraitTemplate {

  private int minimalValue = 0;

  public void setMinimalValue(int minimalValue) {
    this.minimalValue = minimalValue;
  }

  @Override
  public int getMinimalValue() {
    return minimalValue;
  }
}