package net.sf.anathema.basics.item.editor;


import org.eclipse.ui.IEditorPart;

public final class FireDirtyRunnable implements Runnable {
  
  private final IPersistableItemEditor editorPart;
  public FireDirtyRunnable(IPersistableItemEditor editorPart) {
    this.editorPart = editorPart;
  }
  @Override
  public void run() {
    editorPart.firePropertyChange(IEditorPart.PROP_DIRTY);
  }
}