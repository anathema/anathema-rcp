package net.sf.anathema.character.core.properties;

import net.sf.anathema.character.core.character.ICharacter;

import org.eclipse.core.runtime.IExecutableExtension;

public interface IProperty extends IExecutableExtension {

  public boolean has(ICharacter character, String property);
}