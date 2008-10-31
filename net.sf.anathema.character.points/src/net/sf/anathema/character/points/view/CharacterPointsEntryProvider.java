package net.sf.anathema.character.points.view;

import org.eclipse.ui.IEditorInput;

import net.sf.anathema.view.valuelist.IValueEntry;
import net.sf.anathema.view.valuelist.IValueListEntryProvider;

public class CharacterPointsEntryProvider implements IValueListEntryProvider {

  private final PointViewInputStore store = new PointViewInputStore();

  public IValueEntry[] getEntries(IEditorInput editorInput) {
    return store.getEntriesFactory(editorInput).createEntries();
  }
}