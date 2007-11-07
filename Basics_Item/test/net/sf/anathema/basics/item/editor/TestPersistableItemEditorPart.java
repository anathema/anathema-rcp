package net.sf.anathema.basics.item.editor;

import net.sf.anathema.basics.item.IItem;

import org.eclipse.swt.widgets.Composite;

public class TestPersistableItemEditorPart extends AbstractPersistableItemEditorPart<IItem> {

  private boolean itemPartCreated = false;

  @Override
  protected void createPartControlForItem(Composite parent) {
    this.itemPartCreated = true;
  }

  public boolean isItemPartCreated() {
    return itemPartCreated;
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}