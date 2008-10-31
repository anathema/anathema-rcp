package net.sf.anathema.basics.item.editor;

import org.eclipse.ui.IEditorPart;

public class EditorCloser implements IEditorCloser {

  public void close(IEditorPart part) {
    part.getSite().getPage().closeEditor(part, false);
  }
}