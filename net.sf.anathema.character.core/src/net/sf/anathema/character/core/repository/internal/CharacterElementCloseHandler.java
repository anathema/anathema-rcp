package net.sf.anathema.character.core.repository.internal;

import net.sf.anathema.basics.item.editor.EditorCloser;
import net.sf.anathema.basics.item.editor.IEditorCloser;
import net.sf.anathema.basics.repository.treecontent.deletion.ICloseHandler;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelIdentifier;

import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;

public class CharacterElementCloseHandler implements ICloseHandler {

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