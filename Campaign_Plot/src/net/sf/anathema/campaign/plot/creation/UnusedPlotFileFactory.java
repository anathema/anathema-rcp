package net.sf.anathema.campaign.plot.creation;

import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.input.IUnusedFileFactory;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;

public class UnusedPlotFileFactory implements IUnusedFileFactory {

  private final IContainer container;

  public UnusedPlotFileFactory() {
    this(RepositoryUtilities.getProject(PlotRepositoryUtilities.getPlotItemType()));
  }

  public UnusedPlotFileFactory(IContainer container) {
    this.container = container;
  }

  public final IFile createUnusedFile(String fileNameSuggestion) throws CoreException {
    String directoryName = createUnusedDirectoryName(fileNameSuggestion);
    IFolder directory = container.getFolder(new Path(directoryName));
    directory.create(true, true, new NullProgressMonitor());
    return directory.getFile(new Path("main.srs")); //$NON-NLS-1$
  }

  private String createUnusedDirectoryName(String fileNameSuggestion) throws CoreException {
    String fileName = fileNameSuggestion;
    if (!isAlreadyInUse(fileName)) {
      return fileName;
    }
    int count = 1;
    do {
      fileName = fileNameSuggestion + count;
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