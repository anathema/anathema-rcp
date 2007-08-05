package net.sf.anathema.character.freebies;

import net.sf.anathema.character.core.editors.ModelIdentifierProvider;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.view.valuelist.IValueEntry;
import net.sf.anathema.view.valuelist.IValueListInputStore;

import org.eclipse.ui.IEditorInput;

public class FreebiesViewInputStore implements IValueListInputStore {

  private final IFreebiePointEntryFactory factory;

  public FreebiesViewInputStore(IFreebiePointEntryFactory factory) {
    this.factory = factory;
  }

  @Override
  public IValueEntry[] getEntries(IEditorInput editorInput) {
    IModelIdentifier modelIdentifier = new ModelIdentifierProvider().getModelIdentifier(editorInput);
    if (modelIdentifier == null) {
      return new IValueEntry[0];
    }
    ICharacterId characterId = modelIdentifier.getCharacterId();
    return factory.create(characterId);
  }
}