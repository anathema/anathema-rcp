package net.sf.anathema.character.core.fake;

import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelIdentifier;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorInput;

public class CharacterObjectMother {

  public static IEditorInput createCharacterEditorInput(IModelIdentifier modelIdentifier) {
    IEditorInput editorInput = EasyMock.createNiceMock(IEditorInput.class);
    EasyMock.expect(editorInput.getAdapter(IModelIdentifier.class)).andReturn(modelIdentifier);
    EasyMock.replay(editorInput);
    return editorInput;
  }

  public static IEditorInput createCharacterEditorInput(ICharacterId characterIdentifier) {
    return createCharacterEditorInput(new ModelIdentifier(characterIdentifier, "fake")); //$NON-NLS-1$
  }

  public static IEditorInput createNonCharacterEditorInput() {
    return createCharacterEditorInput((IModelIdentifier) null);
  }

  public static IPartContainer createPartContainerWithActiveEditorInput(IEditorInput editedInput) {
    IPartContainer partContainer = EasyMock.createNiceMock(IPartContainer.class);
    EasyMock.expect(partContainer.getEditorInput()).andReturn(editedInput);
    EasyMock.replay(partContainer);
    return partContainer;
  }
}