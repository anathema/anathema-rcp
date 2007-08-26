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
import org.eclipse.ui.IEditorPart;
import org.junit.Before;
import org.junit.Test;

public class FreebiesViewUpdateHandlerModelSwitchTest {

  private FreebiesViewUpdateHandler updateHandler;
  private ModelIdentifier firstIdentifier;
  private ModelIdentifier secondIdentifier;
  private DummyModel secondModel;
  private DummyModel firstModel;

  @Before
  public void createUpdateHandler() throws Exception {
    firstModel = new DummyModel();
    secondModel = new DummyModel();
    firstIdentifier = new ModelIdentifier(new DummyCharacterId(), "hasä"); //$NON-NLS-1$
    secondIdentifier = new ModelIdentifier(new DummyCharacterId(), "ntum"); //$NON-NLS-1$
    IModelProvider modelProvider = EasyMock.createNiceMock(IModelProvider.class);
    EasyMock.expect(modelProvider.getModel(firstIdentifier)).andReturn(firstModel).anyTimes();
    EasyMock.expect(modelProvider.getModel(secondIdentifier)).andReturn(secondModel).anyTimes();
    EasyMock.replay(modelProvider);
    updateHandler = new FreebiesViewUpdateHandler(modelProvider);
  }

  @Test
  public void updatableIsCalledWhenTopModelChanges() throws Exception {
    IUpdatable updatable = EasyMock.createStrictMock(IUpdatable.class);
    updatable.update();
    updatable.update();
    updatable.update();
    InstanceOf matcher = new InstanceOf(TopPartListener.class);
    IPartContainer partContainer = createPartContainer(matcher);
    EasyMock.replay(updatable);
    updateHandler.init(partContainer, updatable);
    ((TopPartListener) matcher.getLastActual()).partBroughtToTop(EasyMock.createMock(IEditorPart.class));
    secondModel.fireModelChanged();
    EasyMock.verify(updatable);
  }

  private IPartContainer createPartContainer(InstanceOf topPartMatcher) {
    IPartContainer partContainer = EasyMock.createNiceMock(IPartContainer.class);
    EasyMock.reportMatcher(topPartMatcher);
    partContainer.addPartListener(null);
    EasyMock.expect(partContainer.getEditorInput()).andReturn(CharacterObjectMother.createCharacterEditorInput(firstIdentifier));
    EasyMock.expect(partContainer.getEditorInput()).andReturn(CharacterObjectMother.createCharacterEditorInput(secondIdentifier));
    EasyMock.replay(partContainer);
    return partContainer;
  }
}