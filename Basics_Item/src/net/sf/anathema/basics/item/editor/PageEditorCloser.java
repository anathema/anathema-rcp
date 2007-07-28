package net.sf.anathema.basics.item.editor;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;

public class PageEditorCloser implements IEditorCloser {

  private final IWorkbenchPage page;

  public PageEditorCloser(IWorkbenchPage page) {
    this.page = page;
  }

  public void close(IEditorPart part) {
    page.closeEditor(part, false);
  }
}