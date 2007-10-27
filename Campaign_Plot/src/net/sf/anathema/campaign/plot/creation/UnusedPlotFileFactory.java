package net.sf.anathema.campaign.plot.creation;

import net.sf.anathema.basics.eclipse.resource.FileUtils;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.input.IUnusedFileFactory;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;

public class UnusedPlotFileFactory implements IUnusedFileFactory {

  private static final String MAIN_FILE_NAME = "main.srs"; //$NON-NLS-1$
  private final IContainer container;

  public UnusedPlotFileFactory() {
    this(RepositoryUtilities.getProject(PlotRepositoryUtilities.getPlotItemType()));
  }

  public UnusedPlotFileFactory(IContainer container) {
    this.container = container;
  }

  public final IFile createUnusedFile(String fileNameSuggestion) throws CoreException {
    IFolder directory = FileUtils.createUnusedFolder(container, fileNameSuggestion);
    directory.create(true, true, new NullProgressMonitor());
    return directory.getFile(new Path(MAIN_FILE_NAME));
  }
}