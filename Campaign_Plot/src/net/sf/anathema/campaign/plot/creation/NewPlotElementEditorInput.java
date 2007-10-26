package net.sf.anathema.campaign.plot.creation;

import java.io.IOException;
import java.net.URL;

import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.repository.input.AbstractNewItemEditorInput;
import net.sf.anathema.basics.repository.input.IUnusedFileFactory;
import net.sf.anathema.campaign.plot.persistence.PlotPersister;
import net.sf.anathema.campaign.plot.repository.IPlotChild;
import net.sf.anathema.campaign.plot.repository.PlotPart;
import net.sf.anathema.campaign.plot.repository.PlotPartPlotChild;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class NewPlotElementEditorInput extends AbstractNewItemEditorInput {

  private final PlotPart parentPart;
  private final IFolder seriesFolder;

  public NewPlotElementEditorInput(
      IUnusedFileFactory unusedFileFactory,
      URL imageUrl,
      String untitledName,
      PlotPart parentPart,
      IFolder seriesFolder) {
    super(unusedFileFactory, imageUrl, untitledName);
    this.parentPart = parentPart;
    this.seriesFolder = seriesFolder;
  }

  @Override
  protected void saveToFile(IProgressMonitor monitor) throws IOException, CoreException, PersistenceException {
    super.saveToFile(monitor);
    String repositoryId = getRepositoryId();
    parentPart.addChild(repositoryId);
    new PlotPersister().saveHierarchy(seriesFolder, parentPart, monitor);
  }

  private String getRepositoryId() {
    IFile file = getFile();
    String name = file.getName();
    return name.substring(0, name.length() - file.getFileExtension().length() - 1);
  }

  @Override
  protected String getFileNameSuggestion(ITitledText item) {
    return parentPart.getPlotUnit().getSuccessor().getName();
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(Class adapter) {
    if (IPlotChild.class.isAssignableFrom(adapter)) {
      return new PlotPartPlotChild(parentPart);
    }
    return super.getAdapter(adapter);
  }
}