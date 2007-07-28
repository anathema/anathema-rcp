package net.sf.anathema.character.core.repository.internal;

import org.eclipse.ui.IEditorReference;

import net.sf.anathema.basics.item.editor.IEditorCloser;

public class CharacterElementCloseHandler {

  private final IEditorCloser closer;
  private final CharacterViewElement element;

  public CharacterElementCloseHandler(IEditorCloser closer, CharacterViewElement element) {
    this.closer = closer;
    this.element = element;
  }

  public void closeIfRequired(IEditorReference reference) {
    throw new UnsupportedOperationException("not yet implemented.");
  }
}