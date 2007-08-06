package net.sf.anathema.view.valuelist;

import org.eclipse.ui.IEditorInput;

public interface IValueListEntryProvider {

  public IValueEntry[] getEntries(IEditorInput editorInput);
}