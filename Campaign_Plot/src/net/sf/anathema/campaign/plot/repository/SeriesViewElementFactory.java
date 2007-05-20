package net.sf.anathema.campaign.plot.repository;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.treecontent.itemtype.IItemTypeViewElementFactory;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.campaign.plot.PlotPlugin;
import net.sf.anathema.campaign.plot.persistence.PlotPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;

public class SeriesViewElementFactory implements IItemTypeViewElementFactory {

  private IItemType itemType;

  @Override
  public List<IViewElement> createViewElements(IViewElement parent) {
    List<IViewElement> elements = new ArrayList<IViewElement>();
    for (IFolder folder : getMembers()) {
      IPlotPart rootPart;
      try {
        convertFileSystem(folder);
        rootPart = new PlotPersister().load(folder);
        elements.add(new PlotElementViewElement(folder, rootPart, parent, itemType.getUntitledName()));
      }
      catch (PersistenceException e) {
        PlotPlugin.log(IStatus.ERROR, "Error loading series.", e);
      }
      catch (CoreException e) {
        PlotPlugin.log(IStatus.ERROR, "Error loading series.", e);
      }
    }
    return elements;
  }

  private void convertFileSystem(IFolder folder) throws CoreException {
    IFile hierarchyFile = folder.getFile(PlotPersister.HIERARCHY_FILE_NAME);
    if (!hierarchyFile.exists()) {
      IFile mainFile = folder.getFile("main.srs"); //$NON-NLS-1$
      IPath destination = folder.getFullPath().append(PlotPersister.HIERARCHY_FILE_NAME);
      mainFile.copy(destination, false, new NullProgressMonitor());
    }
  }

  private List<IFolder> getMembers() {
    IProject project = RepositoryUtilities.getProject(itemType);
    List<IFolder> members = new ArrayList<IFolder>();
    try {
      for (IResource resource : project.members()) {
        if (resource instanceof IFolder) {
          members.add((IFolder) resource);
        }
      }
      return members;
    }
    catch (CoreException e) {
      PlotPlugin.log(IStatus.ERROR, "Could not retrieve project members.", e);
      return new ArrayList<IFolder>();
    }
  }

  @Override
  public void setItemType(IItemType itemType) {
    this.itemType = itemType;
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}