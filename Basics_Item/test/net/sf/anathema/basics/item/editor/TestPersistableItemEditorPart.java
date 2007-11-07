package net.sf.anathema.basics.item.editor;

import net.sf.anathema.basics.item.IItem;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;

public class TestPersistableItemEditorPart extends AbstractPersistableItemEditorPart<IItem> {

  private boolean itemPartCreated = false;
  private boolean initedForItem;
  private boolean focusIsSetForItem;

  @Override
  protected void createPartControlForItem(Composite parent) {
    this.itemPartCreated = true;
  }

  @Override
  protected void initForItem(IEditorSite site, IEditorInput input) {
    initedForItem = true;
  }

  public boolean isItemPartCreated() {
    return itemPartCreated;
  }

  public boolean isInitedForItem() {
    return initedForItem;
  }

  @Override
  protected void setFocusForItem() {
    focusIsSetForItem = true;
  }

  public boolean isFocusIsSetForItem() {
    return focusIsSetForItem;
  }
}