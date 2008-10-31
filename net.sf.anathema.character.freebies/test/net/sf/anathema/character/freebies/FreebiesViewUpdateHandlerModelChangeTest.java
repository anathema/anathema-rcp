package net.sf.anathema.character.freebies;

import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.fake.DummyCharacterId;
import net.sf.anathema.character.core.fake.DummyModel;
import net.sf.anathema.character.freebies.view.FreebiesViewUpdateHandler;
import net.sf.anathema.lib.ui.IUpdatable;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class FreebiesViewUpdateHandlerModelChangeTest {

  private FreebiesViewUpdateHandler updateHandler;
  private ModelIdentifier modelIdentifier;
  private DummyModel dummyModel;

  @Before
  public void createUpdateHandler() throws Exception {
    dummyModel = new DummyModel();
    modelIdentifier = new ModelIdentifier(new DummyCharacterId(), "hasä"); //$NON-NLS-1$
    IModelCollection modelProvider = CharacterObjectMother.createModelProvider(modelIdentifier, dummyModel);
    updateHandler = new FreebiesViewUpdateHandler(modelProvider);
  }

  @Test
  public void updatableIsCalledWhenInitalTopModelChanges() throws Exception {
    IUpdatable updatable = EasyMock.createStrictMock(IUpdatable.class);
    updatable.update();
    updatable.update();
    IPartContainer partContainer = CharacterObjectMother.createPartContainerWithActiveEditorInput(CharacterObjectMother.createCharacterEditorInput(modelIdentifier));
    EasyMock.replay(updatable);
    updateHandler.init(partContainer, updatable);
    dummyModel.fireModelChanged();
    EasyMock.verify(updatable);
  }
}