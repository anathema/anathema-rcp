package net.sf.anathema.campaign.plot.repository;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.treecontent.itemtype.AbstractFolderBasedViewElementFactory;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.campaign.plot.PlotPlugin;
import net.sf.anathema.campaign.plot.persistence.PlotPersister;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;

public class PlotViewElementFactory extends AbstractFolderBasedViewElementFactory {

  @Override
  public List<IViewElement> createViewElements(IViewElement parent) {
    List<IViewElement> elements = new ArrayList<IViewElement>();
    for (IFolder folder : getMembers()) {
      IPlotPart rootPart;
      try {
        convertFileSystem(folder);
        rootPart = new PlotPersister().load(folder);
        elements.add(new PlotElementViewElement(folder, rootPart, parent, getItemType().getUntitledName()));
      }
      catch (Exception e) {
        PlotPlugin.getDefaultInstance().log(IStatus.ERROR, Messages.PlotViewElementFactory_LoadingPlotErrorMessage, e);
      }
    }
    return elements;
  }

  // TODO beim Startup Konvertieren
  private void convertFileSystem(IFolder folder) throws CoreException {
    IFile hierarchyFile = folder.getFile(PlotPersister.HIERARCHY_FILE_NAME);
    if (!hierarchyFile.exists()) {
      IFile mainFile = folder.getFile("main.srs"); //$NON-NLS-1$
      IPath destination = folder.getFullPath().append(PlotPersister.HIERARCHY_FILE_NAME);
      mainFile.copy(destination, false, new NullProgressMonitor());
    }
  }
}