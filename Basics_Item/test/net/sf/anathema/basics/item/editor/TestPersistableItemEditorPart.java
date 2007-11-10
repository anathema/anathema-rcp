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
  protected IEditorControl createItemEditorControl() {
    return new IEditorControl() {

      @Override
      public void createPartControl(Composite parent) {
        itemPartCreated = true;
      }

      @Override
      public void init(IEditorSite editorSite, IEditorInput input) {
        initedForItem = true;
      }

      @Override
      public boolean isDirty() {
        return false;
      }

      @Override
      public void setFocus() {
        focusIsSetForItem = true;
      }
    };
  }

  public boolean isItemPartCreated() {
    return itemPartCreated;
  }

  public boolean isInitedForItem() {
    return initedForItem;
  }

  public boolean isFocusIsSetForItem() {
    return focusIsSetForItem;
  }
}