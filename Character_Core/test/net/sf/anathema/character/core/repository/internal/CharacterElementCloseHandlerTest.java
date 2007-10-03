package net.sf.anathema.character.core.repository.internal;

import static org.junit.Assert.*;
import net.sf.anathema.basics.item.editor.DummyCloser;
import net.sf.anathema.basics.jface.IFileEditorInput;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.character.internal.CharacterId;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFolder;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.junit.Before;
import org.junit.Test;

public class CharacterElementCloseHandlerTest {

  private CharacterElementCloseHandler handler;
  private DummyCloser closer;
  private IFileEditorInput input;
  private IEditorReference reference;
  private CharacterId characterId;

  @Before
  public void createHandler() throws PartInitException {
    this.closer = new DummyCloser();
    this.characterId = new CharacterId(EasyMock.createMock(IFolder.class));
    this.handler = new CharacterElementCloseHandler(closer, characterId);
    this.input = EasyMock.createMock(IFileEditorInput.class);
    this.reference = EasyMock.createNiceMock(IEditorReference.class);
    EasyMock.expect(reference.getEditorInput()).andReturn(input);
  }

  @Test
  public void closesEditorsForSameCharacter() throws Exception {
    IEditorPart editor = EasyMock.createMock(IEditorPart.class);
    closer.setExpectedEditor(editor);
    EasyMock.expect(reference.getEditor(false)).andReturn(editor);
    EasyMock.expect(input.getAdapter(IModelIdentifier.class)).andReturn(new ModelIdentifier(characterId, "test")); //$NON-NLS-1$
    EasyMock.replay(reference, input);
    handler.closeIfRequired(reference);
    assertTrue(closer.isClosed());
  }

  @Test
  public void doesNotCloseNonCharacterEditors() throws Exception {
    EasyMock.expect(input.getAdapter(IModelIdentifier.class)).andReturn(null);
    EasyMock.replay(reference, input);
    handler.closeIfRequired(reference);
    assertFalse(closer.isClosed());
  }

  @Test
  public void doesNotCloseEditorsForDifferentCharacters() throws Exception {
    ICharacterId otherId = new CharacterId(EasyMock.createMock(IFolder.class));
    EasyMock.expect(input.getAdapter(IModelIdentifier.class)).andReturn(new ModelIdentifier(otherId, "test")); //$NON-NLS-1$
    EasyMock.replay(reference, input);
    handler.closeIfRequired(reference);
    assertFalse(closer.isClosed());
  }
}
