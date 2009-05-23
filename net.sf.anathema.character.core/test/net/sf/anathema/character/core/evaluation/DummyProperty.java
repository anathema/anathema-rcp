package net.sf.anathema.character.core.evaluation;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.properties.IProperty;

public class DummyProperty extends UnconfiguredExecutableExtension implements IProperty {

  private final String trueValue;

  public DummyProperty(String trueValue) {
    this.trueValue = trueValue;
  }

  @Override
  public boolean has(ICharacter character, String property) {
    return trueValue.equals(property);
  }
}