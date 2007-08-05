package net.sf.anathema.character.freebies;

import net.sf.anathema.view.valuelist.IValueEntry;
import net.sf.anathema.view.valuelist.IValueListInputStore;

import org.eclipse.ui.IEditorInput;

public class FreebiesViewInputStore implements IValueListInputStore {

  @Override
  public IValueEntry[] getEntries(IEditorInput editorInput) {
    return new IValueEntry[0];
  }
}