package net.sf.anathema.basics.repository.input;

import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.itemtype.IItemType;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

public class UnusedFileFactory implements IUnusedFileFactory {

  private final IContainer container;
  private final String fileExtension;


  public UnusedFileFactory(IItemType itemType) {
    this(RepositoryUtilities.getProject(itemType), itemType.getFileExtension());
  }

  public UnusedFileFactory(IContainer container, String fileExtension) {
    this.container = container;
    this.fileExtension = fileExtension;
  }

  public final IFile createUnusedFile(String fileNameSuggestion) throws CoreException {
    String fileName = createUnusedFileName(fileNameSuggestion);
    return container.getFile(new Path(fileName));
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