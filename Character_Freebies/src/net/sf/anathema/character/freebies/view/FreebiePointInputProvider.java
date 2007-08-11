package net.sf.anathema.character.freebies.view;

import net.sf.anathema.character.core.editors.ModelIdentifierProvider;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.view.valuelist.IValueEntry;
import net.sf.anathema.view.valuelist.IValueListEntryProvider;

import org.eclipse.ui.IEditorInput;

public class FreebiePointInputProvider implements IValueListEntryProvider {

  private final IFreebiePointEntryFactory factory;

  public FreebiePointInputProvider(IFreebiePointEntryFactory factory) {
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