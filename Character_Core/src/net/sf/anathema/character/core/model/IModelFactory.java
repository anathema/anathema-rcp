package net.sf.anathema.character.core.model;

import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtension;

public interface IModelFactory extends IExecutableExtension {

  public IModel create(IFile modelFile) throws PersistenceException, CoreException;
}