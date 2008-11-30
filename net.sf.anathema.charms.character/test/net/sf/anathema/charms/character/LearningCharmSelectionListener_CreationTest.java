package net.sf.anathema.charms.character;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.charms.character.model.CharmModel;

import org.junit.Before;
import org.junit.Test;

public class LearningCharmSelectionListener_CreationTest {

  public static final String CHARM_ID = "charmId"; //$NON-NLS-1$
  private CharmModel charmModel;
  private LearningCharmSelectionListener listener;

  @Before
  public void createsCharmModel() {
    charmModel = new CharmModel();
    listener = new LearningCharmSelectionListener(charmModel, new DummyExperience(false));
  }

  @Test
  public void learnsCharmByCreationOnSelection() throws Exception {
    listener.charmSelected(CHARM_ID);
    assertThat(charmModel.isCreationLearned(CHARM_ID), is(true));
  }

  @Test
  public void forgetsCreationLearnedCharmOnSelection() throws Exception {
    charmModel.toggleCreationLearned(CHARM_ID);
    listener.charmSelected(CHARM_ID);
    assertThat(charmModel.isLearned(CHARM_ID), is(false));
  }

  @Test
  public void doesNotForgetExperienceLearnedCharmOnSelection() throws Exception {
    charmModel.toggleExperiencedLearned(CHARM_ID);
    listener.charmSelected(CHARM_ID);
    assertThat(charmModel.isCreationLearned(CHARM_ID), is(false));
    assertThat(charmModel.isLearned(CHARM_ID), is(true));
  }
}