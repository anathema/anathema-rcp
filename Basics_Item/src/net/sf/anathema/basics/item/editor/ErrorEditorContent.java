package net.sf.anathema.basics.item.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;

public final class ErrorEditorContent implements IEditorContent {
  private ErrorMessageEditorInput input;

  @Override
  public void setFocus() {
    // nothing to do
  }

  @Override
  public void init(IEditorSite editorSite, IEditorInput editorInput) {
    this.input = (ErrorMessageEditorInput) editorInput;
  }

  @Override
  public void createPartControl(Composite parent) {
    new Label(parent, SWT.NONE).setText(input.getMessage());
  }

  @Override
  public boolean isDirty() {
    return false;
  }
}