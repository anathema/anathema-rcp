package net.sf.anathema.character.core.character;

import org.eclipse.core.runtime.IAdaptable;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;

public interface ICharacterId extends IAdaptable {

  public IContentHandle getContents(String fileName);
}