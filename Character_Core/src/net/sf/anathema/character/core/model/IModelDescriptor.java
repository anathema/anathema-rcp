package net.sf.anathema.character.core.model;

import net.sf.anathema.character.core.character.ICharacterTemplate;

import org.eclipse.core.runtime.IExecutableExtension;

public interface IModelDescriptor extends IExecutableExtension {

  public boolean isSupportedBy(ICharacterTemplate template);
}