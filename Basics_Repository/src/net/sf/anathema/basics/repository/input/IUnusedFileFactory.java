package net.sf.anathema.basics.repository.input;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

public interface IUnusedFileFactory {

  public IFile createUnusedFile(String fileNameSuggestion) throws CoreException;
}