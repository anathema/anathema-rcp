package net.sf.anathema.character.core.properties.evaluation;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.properties.IProperty;

public class HasProperty implements IPredicate<ICharacter> {

  private final IProperty property;
  private final String propertyValue;

  public HasProperty(IProperty propertyTester, String propertyValue) {
    this.property = propertyTester;
    this.propertyValue = propertyValue;
  }

  @Override
  public boolean evaluate(ICharacter character) {
    return property.has(character, propertyValue);
  }
}