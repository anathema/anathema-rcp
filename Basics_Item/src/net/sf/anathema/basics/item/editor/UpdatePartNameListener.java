package net.sf.anathema.basics.item.editor;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public final class UpdatePartNameListener implements IObjectValueChangedListener<String> {
  
  private final IPersistableItemEditor editorPart;
  public UpdatePartNameListener(IPersistableItemEditor editorPart) {
    this.editorPart = editorPart;
  }

  @Override
  public void valueChanged(String newValue) {
    editorPart.setPartName(editorPart.getPersistableEditorInput().getName());
  }
}