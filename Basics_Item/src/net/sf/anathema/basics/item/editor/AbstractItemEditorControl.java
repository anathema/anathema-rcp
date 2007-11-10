package net.sf.anathema.basics.item.editor;


public abstract class AbstractItemEditorControl implements IEditorControl {

  private final IPersistableItemEditor editor;

  public AbstractItemEditorControl(IPersistableItemEditor editor) {
    this.editor = editor;
  }

  @Override
  public final boolean isDirty() {
    return editor.getPersistableEditorInput().getItem().isDirty();
  }
}