package net.sf.anathema.character.core.editors;

import net.sf.anathema.basics.eclipse.ui.IEditorInputProvider;
import net.sf.anathema.character.core.model.IModelIdentifier;

import org.eclipse.ui.IEditorInput;

public class ModelIdentifierProvider {

  public IModelIdentifier getModelIdentifier(IEditorInputProvider inputProvider) {
    if (inputProvider == null) {
      return null;
    }
    IEditorInput editorInput = inputProvider.getEditorInput();
    return (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
  }
}