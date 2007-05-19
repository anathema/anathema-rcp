package net.sf.anathema.basics.repository.input;

import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.itemtype.IItemType;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

public class UnusedFileFactory {


  public IFile createUnusedFile(String fileNameSuggestion, IItemType itemType) {
    IProject project = RepositoryUtilities.getProject(itemType);
    String fileExtension = itemType.getFileExtension();
    IFile file = project.getFile(fileNameSuggestion + "." + fileExtension); //$NON-NLS-1$
    if (!isAlreadyInUse(file)) {
      return file;
    }
    int count = 1;
    do {
      file = project.getFile(fileNameSuggestion + count + "." + fileExtension); //$NON-NLS-1$
      count++;
    }
    while (isAlreadyInUse(file));
    return file;
  }

  private boolean isAlreadyInUse(IFile file) {
    return file.exists();
  }
}