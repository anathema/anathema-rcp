package net.sf.anathema.character.core.editors;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.fake.DummyCharacterId;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorInput;
import org.junit.Before;
import org.junit.Test;

public class CharacterIdentiferProvider_Test {

  private IEditorInput createInput(IModelIdentifier modelIdentifer) {
    final IEditorInput editorInput = EasyMock.createMock(IEditorInput.class);
    EasyMock.expect(editorInput.getAdapter(IModelIdentifier.class)).andReturn(modelIdentifer);
    EasyMock.replay(editorInput);
    return editorInput;
  }

  private CharacterIdProvider idProvider;

  @Before
  public void createIdentifierProvider() throws Exception {
    this.idProvider = new CharacterIdProvider();
  }

  @Test(expected = NullPointerException.class)
  public void doesNotAcceptNullInput() throws Exception {
    idProvider.getCharacterId(null);
  }

  @Test
  public void returnsNullForNonCharacterEditorInput() throws Exception {
    IEditorInput input = createInput(null);
    ICharacterId returnedId = idProvider.getCharacterId(input);
    assertThat(returnedId, is(nullValue()));
  }

  @Test
  public void createsModelIdentifierForCharacterEditorInput() throws Exception {
    ICharacterId id = new DummyCharacterId();
    IModelIdentifier modelIdentifier = createModelIdentifier(id);
    IEditorInput input = createInput(modelIdentifier);
    ICharacterId returnedId = idProvider.getCharacterId(input);
    assertThat(returnedId, is(id));
  }

  private IModelIdentifier createModelIdentifier(ICharacterId id) {
    IModelIdentifier modelIdentifier = EasyMock.createMock(IModelIdentifier.class);
    expect(modelIdentifier.getCharacterId()).andReturn(id);
    replay(modelIdentifier);
    return modelIdentifier;
  }
}