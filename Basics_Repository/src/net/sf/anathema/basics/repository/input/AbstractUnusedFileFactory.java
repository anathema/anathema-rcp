package net.sf.anathema.basics.repository.input;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public abstract class AbstractUnusedFileFactory<C extends IContainer> implements IUnusedFileFactory {

  private final C container;
  private final String fileExtension;

  public AbstractUnusedFileFactory(C container, String fileExtension) {
    this.container = container;
    this.fileExtension = fileExtension;
  }

  public final IFile createUnusedFile(String fileNameSuggestion) throws CoreException {
    String fileName = createUnusedFileName(fileNameSuggestion);
    return createFile(fileName);
  }

  protected abstract IFile createFile(String fileName);
  
  protected final C getContainer() {
    return container;
  }

  private String createUnusedFileName(String fileNameSuggestion) throws CoreException {
    String fileName = fileNameSuggestion + "." + fileExtension; //$NON-NLS-1$
    if (!isAlreadyInUse(fileName)) {
      return fileName;
    }
    int count = 1;
    do {
      fileName = fileNameSuggestion + count + "." + fileExtension; //$NON-NLS-1$
      count++;
    }
    while (isAlreadyInUse(fileName));
    return fileName;
  }

  private boolean isAlreadyInUse(String fileName) throws CoreException {
    for (IResource member : container.members()) {
      if (fileName.equalsIgnoreCase(member.getName())) {
        return true;
      }
    }
    return false;
  }
}