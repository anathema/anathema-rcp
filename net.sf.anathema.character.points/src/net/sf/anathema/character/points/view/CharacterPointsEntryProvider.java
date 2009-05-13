package net.sf.anathema.character.points.view;

import java.util.List;

import net.sf.anathema.view.valuelist.IValueEntry;
import net.sf.anathema.view.valuelist.IValueListEntryProvider;

import org.eclipse.ui.IEditorInput;

public class CharacterPointsEntryProvider implements IValueListEntryProvider {

  private final PointViewInputStore store = new PointViewInputStore();

  public List<IValueEntry> getEntries(IEditorInput editorInput) {
    return store.getEntriesFactory(editorInput).createEntries();
  }
}