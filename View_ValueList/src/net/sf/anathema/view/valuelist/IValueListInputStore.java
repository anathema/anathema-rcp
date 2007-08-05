package net.sf.anathema.view.valuelist;

import org.eclipse.ui.IEditorInput;

public interface IValueListInputStore {

  public IValueEntry[] getEntries(IEditorInput editorInput);
}