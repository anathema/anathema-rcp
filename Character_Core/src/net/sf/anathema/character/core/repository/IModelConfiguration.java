package net.sf.anathema.character.core.repository;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

public interface IModelConfiguration {

  public IFile getModelFile(IFolder characterFolder);
}
