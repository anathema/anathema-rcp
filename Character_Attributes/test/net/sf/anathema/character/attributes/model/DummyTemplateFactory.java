package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.trait.model.IMinimalValueFactory;

public final class DummyTemplateFactory implements IMinimalValueFactory {
  private final int minimalValue;

  public DummyTemplateFactory() {
    this(0);
  }

  public DummyTemplateFactory(int minimalValue) {
    this.minimalValue = minimalValue;
  }

  @Override
  public int getMinimalValue(String traitId) {
    return minimalValue;
  }
}