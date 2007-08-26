package net.sf.anathema.character.freebies;

import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.basics.eclipse.ui.TopPartListener;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.fake.DummyCharacterId;
import net.sf.anathema.character.core.fake.DummyModel;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.freebies.view.FreebiesViewUpdateHandler;
import net.sf.anathema.test.matcher.InstanceOf;
import net.sf.anathema.view.valuelist.IUpdatable;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
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
    IModelProvider modelProvider = CharacterObjectMother.createModelProvider(modelIdentifier, dummyModel);
    updateHandler = new FreebiesViewUpdateHandler(modelProvider);
  }

  @Test
  public void updatableIsCalledWhenTopModelChanges() throws Exception {
    IUpdatable updatable = EasyMock.createStrictMock(IUpdatable.class);
    updatable.update();
    updatable.update();
    initHandlerForModelTopPart(updatable);
    dummyModel.fireModelChanged();
    EasyMock.verify(updatable);
  }

  private void initHandlerForModelTopPart(IUpdatable updatable) {
    InstanceOf matcher = new InstanceOf(TopPartListener.class);
    IPartContainer partContainer = createPartContainer(CharacterObjectMother.createCharacterEditorInput(modelIdentifier), matcher);
    EasyMock.replay(updatable);
    updateHandler.init(partContainer, updatable);
    ((TopPartListener) matcher.getLastActual()).partBroughtToTop(EasyMock.createMock(IEditorPart.class));
  }

  private IPartContainer createPartContainer(IEditorInput editorInput, InstanceOf topPartMatcher) {
    IPartContainer partContainer = EasyMock.createNiceMock(IPartContainer.class);
    EasyMock.reportMatcher(topPartMatcher);
    partContainer.addPartListener(null);
    EasyMock.expect(partContainer.getEditorInput()).andReturn(editorInput).anyTimes();
    EasyMock.replay(partContainer);
    return partContainer;
  }
}