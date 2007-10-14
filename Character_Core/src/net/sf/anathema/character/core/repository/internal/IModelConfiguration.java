package net.sf.anathema.character.core.repository.internal;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;

public interface IModelConfiguration {

  public IFile getModelFile(IContainer characterFolder);
}