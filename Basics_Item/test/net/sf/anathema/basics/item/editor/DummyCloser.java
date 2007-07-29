package net.sf.anathema.basics.item.editor;

import org.eclipse.ui.IEditorPart;

public class DummyCloser implements IEditorCloser {

  private boolean closed;
  private IEditorPart editor;

  @Override
  public void close(IEditorPart editorPart) {
    if (editor == null || editor.equals(editorPart)) {
      this.closed = true;
    }
  }

  public boolean isClosed() {
    return closed;
  }

  public void setExpectedEditor(IEditorPart editor) {
    this.editor = editor;
  }
}
