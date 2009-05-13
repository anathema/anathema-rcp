package net.sf.anathema.character.freebies.view;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.editors.CharacterIdProvider;
import net.sf.anathema.view.valuelist.IValueEntry;
import net.sf.anathema.view.valuelist.IValueListEntryProvider;

import org.eclipse.ui.IEditorInput;

public class FreebiePointInputProvider implements IValueListEntryProvider {

  private final IFreebiePointEntryFactory factory;

  public FreebiePointInputProvider(IFreebiePointEntryFactory factory) {
    this.factory = factory;
  }

  @Override
  public List<IValueEntry> getEntries(IEditorInput editorInput) {
    ICharacterId characterId = new CharacterIdProvider().getCharacterId(editorInput);
    if (characterId == null) {
      return new ArrayList<IValueEntry>();
    }
    return factory.create(characterId);
  }
}