package net.sf.anathema.basics.repository.input;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.data.IBasicItemData;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;

import org.eclipse.jface.resource.ImageDescriptor;

public class NewItemEditorInput extends AbstractNewItemEditorInput {

  public NewItemEditorInput(IItemType itemType) {
    super(
        new UnusedFileFactory(itemType),
        ImageDescriptor.createFromURL(itemType.getIconUrl()),
        itemType.getUntitledName());
  }

  @Override
  protected String getFileNameSuggestion(IItem<IBasicItemData> item) {
    return AnathemaStringUtilities.getFileNameRepresentation(item.getPrintName());
  }
}