package net.sf.anathema.character.core.fake;

import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorInput;

public class CharacterObjectMother {

  public static IEditorInput createCharacterEditorInput(IModelIdentifier modelIdentifier) {
    IEditorInput editorInput = EasyMock.createNiceMock(IEditorInput.class);
    EasyMock.expect(editorInput.getAdapter(IModelIdentifier.class)).andReturn(modelIdentifier).anyTimes();
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
    EasyMock.expect(partContainer.getEditorInput()).andReturn(editedInput).anyTimes();
    EasyMock.replay(partContainer);
    return partContainer;
  }

  public static IModelCollection createModelProvider(IModelIdentifier identifier, IModel model) {
    IModelCollection modelProvider = EasyMock.createNiceMock(IModelCollection.class);
    EasyMock.expect(modelProvider.getModel(identifier)).andReturn(model).anyTimes();
    EasyMock.replay(modelProvider);
    return modelProvider;
  }
}