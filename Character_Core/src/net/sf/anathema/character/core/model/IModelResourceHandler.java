package net.sf.anathema.character.core.model;

import net.sf.anathema.character.core.character.IModelIdentifier;

import org.eclipse.core.resources.IResource;

public interface IModelResourceHandler {

  public IResource getResource(IModelIdentifier identifier);
}