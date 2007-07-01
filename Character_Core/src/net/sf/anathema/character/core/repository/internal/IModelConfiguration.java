package net.sf.anathema.character.core.repository.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

public interface IModelConfiguration {

  public IFile getModelFile(IFolder characterFolder);
}
