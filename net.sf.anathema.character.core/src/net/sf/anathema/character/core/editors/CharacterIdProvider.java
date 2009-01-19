package net.sf.anathema.character.core.editors;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelIdentifier;

import org.eclipse.ui.IEditorInput;

public class CharacterIdProvider {

  public ICharacterId getCharacterId(IEditorInput editorInput) {
    IModelIdentifier identifier = (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
    if (identifier == null) {
      return null;
    }
    return identifier.getCharacterId();
  }
}