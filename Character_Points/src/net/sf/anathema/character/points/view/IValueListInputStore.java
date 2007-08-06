package net.sf.anathema.character.points.view;

import org.eclipse.ui.IEditorInput;

public interface IValueListInputStore {

  public ICharacterValueEntryFactory getEntriesFactory(IEditorInput editorInput);
}