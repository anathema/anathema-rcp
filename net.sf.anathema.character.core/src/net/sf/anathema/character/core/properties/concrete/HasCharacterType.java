package net.sf.anathema.character.core.properties.concrete;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.properties.IProperty;

public class HasCharacterType extends UnconfiguredExecutableExtension implements IProperty {

  @Override
  public boolean has(ICharacter character, String property) {
    return character.getCharacterType().getId().equals(property);
  }
}