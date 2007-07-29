package net.sf.anathema.character.core.repository.internal;

import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;

import net.sf.anathema.basics.item.editor.EditorCloser;
import net.sf.anathema.basics.item.editor.IEditorCloser;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelIdentifier;

public class CharacterElementCloseHandler {

  private final IEditorCloser closer;
  private final ICharacterId id;

  public CharacterElementCloseHandler(ICharacterId id) {
    this(new EditorCloser(), id);
  }

  public CharacterElementCloseHandler(IEditorCloser closer, ICharacterId id) {
    this.closer = closer;
    this.id = id;
  }

  public void closeIfRequired(IEditorReference reference) throws PartInitException {
    IModelIdentifier identifier = (IModelIdentifier) reference.getEditorInput().getAdapter(IModelIdentifier.class);
    if (identifier != null && identifier.getCharacterId().equals(id)) {
      closer.close(reference.getEditor(false));
    }
  }
}