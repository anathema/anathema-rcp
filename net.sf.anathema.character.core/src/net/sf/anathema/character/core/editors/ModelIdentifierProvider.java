package net.sf.anathema.character.core.editors;

import net.sf.anathema.character.core.character.IModelIdentifier;

import org.eclipse.ui.IEditorInput;

public class ModelIdentifierProvider {

  public IModelIdentifier getModelIdentifier(IEditorInput editorInput) {
    if (editorInput == null) {
      return null;
    }
    return (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
  }
}