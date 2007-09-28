package net.sf.anathema.campaign.plot;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.campaign.plot.creation.PlotRepositoryUtilities;
import net.sf.anathema.campaign.plot.persistence.PlotPersister;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.osgi.framework.BundleContext;

public class PlotPlugin extends AbstractAnathemaUIPlugin {

  public static final String ID = "net.sf.anathema.campaign.plot";//$NON-NLS-1$
  public static final String PLOT_EDITOR_ID = "net.sf.anathema.campaign.plot.ploteditor"; //$NON-NLS-1$
  private static PlotPlugin instance;

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    convertFileSystems();
  }

  private void convertFileSystems() throws CoreException {
    for (IFolder folder : RepositoryUtilities.getItemFolders(PlotRepositoryUtilities.getPlotItemType())) {
      convertFileSystem(folder);
    }
  }

  // TODO XSLT statt Kopie
  // TODO Importwizard
  private void convertFileSystem(IFolder folder) throws CoreException {
    IFile hierarchyFile = folder.getFile(PlotPersister.HIERARCHY_FILE_NAME);
    if (!hierarchyFile.exists()) {
      IFile mainFile = folder.getFile("main.srs"); //$NON-NLS-1$
      IPath destination = folder.getFullPath().append(PlotPersister.HIERARCHY_FILE_NAME);
      mainFile.copy(destination, false, new NullProgressMonitor());
    }
  }

  @Override
  protected void createInstance() {
    instance = this;
  }

  @Override
  protected void deleteInstance() {
    instance = null;
  }

  @Override
  protected AbstractAnathemaUIPlugin getPluginInstance() {
    return getDefaultInstance();
  }

  public static PlotPlugin getDefaultInstance() {
    return instance;
  }
}