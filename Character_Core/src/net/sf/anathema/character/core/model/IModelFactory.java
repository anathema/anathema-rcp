package net.sf.anathema.character.core.model;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IExecutableExtension;

public interface IModelFactory extends IExecutableExtension {

  public IModel create(IFolder characterFolder);
}