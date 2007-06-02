package net.sf.anathema.basics.repository.input;

import net.sf.anathema.basics.repository.itemtype.IItemType;

import org.eclipse.jface.resource.ImageDescriptor;

public class NewItemEditorInput extends AbstractNewItemEditorInput {

  public NewItemEditorInput(IItemType itemType) {
    super(
        new UnusedFileFactory(itemType),
        ImageDescriptor.createFromURL(itemType.getIconUrl()),
        itemType.getUntitledName());
  }
}