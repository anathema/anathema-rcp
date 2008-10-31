package net.sf.anathema.charms.character;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.disy.commons.core.model.listener.IChangeListener;

import org.junit.Before;
import org.junit.Test;

public class CharmModel_EmptyTest {

  private static final String CHARM_ID = "heaven"; //$NON-NLS-1$
  private CharmModel charmModel;

  @Before
  public void createModel() throws Exception {
    charmModel = new CharmModel();
  }

  @Test
  public void isDirty() throws Exception {
    assertThat(charmModel.isDirty(), is(true));
  }

  @Test
  public void hasNotLearnedCharmOnCreation() throws Exception {
    assertThat(charmModel.isCreationLearned(CHARM_ID), is(false));
  }

  @Test
  public void learnsUnknownCharmOnToggleLearned() throws Exception {
    charmModel.toggleCreationLearned(CHARM_ID);
    assertThat(charmModel.isCreationLearned(CHARM_ID), is(true));
  }

  @Test
  public void dirtiesOnLearningCharm() throws Exception {
    charmModel.setClean();
    charmModel.toggleCreationLearned(CHARM_ID);
    assertThat(charmModel.isDirty(), is(true));
  }

  @Test
  public void notifiesChangeListenerOnCharmLearn() throws Exception {
    IChangeListener listener = createMock(IChangeListener.class);
    listener.stateChanged();
    replay(listener);
    charmModel.addChangeListener(listener);
    charmModel.toggleCreationLearned(CHARM_ID);
    verify(listener);
  }

  @Test
  public void doesNotNotifiyRemovedListeners() throws Exception {
    IChangeListener listener = createMock(IChangeListener.class);
    replay(listener);
    charmModel.addChangeListener(listener);
    charmModel.removeChangeListener(listener);
    charmModel.toggleCreationLearned(CHARM_ID);
    verify(listener);
  }
}