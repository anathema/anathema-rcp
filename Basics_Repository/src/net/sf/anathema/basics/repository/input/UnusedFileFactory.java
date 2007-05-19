package net.sf.anathema.basics.repository.input;

import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.itemtype.IItemType;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class UnusedFileFactory implements IUnusedFileFactory {

  private final IProject project;
  private final String fileExtension;

  public UnusedFileFactory(IItemType itemType) {
    this(RepositoryUtilities.getProject(itemType), itemType.getFileExtension());
  }

  public UnusedFileFactory(IProject project, String fileExtension) {
    this.project = project;
    this.fileExtension = fileExtension;
  }

  public IFile createUnusedFile(String fileNameSuggestion) throws CoreException {
    String fileName = createUnusedFileName(fileNameSuggestion);
    return project.getFile(fileName);
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
    for (IResource member : project.members()) {
      if (fileName.equalsIgnoreCase(member.getName())) {
        return true;
      }
    }
    return false;
  }
}