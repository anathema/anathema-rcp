package net.sf.anathema.view.valuelist;

import org.eclipse.ui.IEditorInput;

public interface IValueListInputStore {

  public IValueEntryFactory getViewInput(IEditorInput editorInput);
}