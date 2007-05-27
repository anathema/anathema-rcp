package net.sf.anathema.campaign.plot.addelement;

import java.io.IOException;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.data.IBasicItemData;
import net.sf.anathema.basics.item.persistence.BasicDataItemPersister;
import net.sf.anathema.basics.repository.input.AbstractNewItemEditorInput;
import net.sf.anathema.basics.repository.input.IUnusedFileFactory;
import net.sf.anathema.campaign.plot.persistence.PlotPersister;
import net.sf.anathema.campaign.plot.repository.PlotPart;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;

public class NewPlotElementEditorInput extends AbstractNewItemEditorInput {

  private final PlotPart parentPart;
  private final IFolder seriesFolder;

  public NewPlotElementEditorInput(
      IUnusedFileFactory unusedFileFactory,
      ImageDescriptor imageDescriptor,
      String untitledName,
      PlotPart parentPart,
      IFolder seriesFolder) {
    super(unusedFileFactory, imageDescriptor, untitledName);
    this.parentPart = parentPart;
    this.seriesFolder = seriesFolder;
  }

  @Override
  protected void saveToFile(BasicDataItemPersister persister) throws IOException, CoreException, PersistenceException {
    super.saveToFile(persister);
    String repositoryId = getRepositoryId();
    parentPart.addChild(repositoryId);
    new PlotPersister().saveHierarchy(seriesFolder, parentPart);
  }

  private String getRepositoryId() {
    IFile file = getFile();
    String name = file.getName();
    return name.substring(0, name.length() - file.getFileExtension().length() - 1);
  }

  @Override
  protected String getFileNameSuggestion(IItem<IBasicItemData> item) {
    return parentPart.getPlotUnit().getSuccessor().getPersistenceString();
  }
}