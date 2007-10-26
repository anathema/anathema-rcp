package net.sf.anathema.basics.repository.input;

import net.sf.anathema.basics.repository.itemtype.IItemType;

public class NewItemEditorInput extends AbstractNewItemEditorInput {

  public NewItemEditorInput(IItemType itemType) {
    super(new UnusedFileFactory(itemType), itemType.getIconUrl(), itemType.getUntitledName());
  }
}