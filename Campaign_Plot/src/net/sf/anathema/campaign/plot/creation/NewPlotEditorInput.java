package net.sf.anathema.campaign.plot.creation;

import java.io.IOException;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.data.IBasicItemData;
import net.sf.anathema.basics.item.persistence.BasicDataItemPersister;
import net.sf.anathema.basics.repository.input.AbstractNewItemEditorInput;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.campaign.plot.persistence.PlotPersister;
import net.sf.anathema.campaign.plot.repository.PlotPart;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;

public class NewPlotEditorInput extends AbstractNewItemEditorInput {

  public NewPlotEditorInput(IItemType itemType) {
    super(
        new UnusedPlotFileFactory(itemType),
        ImageDescriptor.createFromURL(itemType.getIconUrl()),
        itemType.getUntitledName());
  }

  @Override
  protected void saveToFile(BasicDataItemPersister persister, IProgressMonitor monitor) throws IOException, CoreException, PersistenceException {
    super.saveToFile(persister, monitor);
    IFolder folder = (IFolder) getFile().getParent();
    new PlotPersister().saveHierarchy(folder, PlotPart.createPlotRoot(), monitor);
  }

  @Override
  protected String getFileNameSuggestion(IItem<IBasicItemData> item) {
    return AnathemaStringUtilities.getFileNameRepresentation(item.getPrintName());
  }
}