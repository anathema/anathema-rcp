package net.sf.anathema.charms.character;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.charms.character.model.CharmModel;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Before;
import org.junit.Test;

public class LearningCharmSelectionListener_ExperienceTest {

  public static final ICharmId CHARM_ID = new CharmId("charmId", null); //$NON-NLS-1$
  private CharmModel charmModel;
  private LearningCharmSelectionListener listener;

  @Before
  public void createsCharmModel() {
    charmModel = new CharmModel();
    listener = new LearningCharmSelectionListener(charmModel, new DummyExperience(true), new DummyCharmPreferences());
  }

  @Test
  public void learnsUnknownCharmByExperienceOnSelection() throws Exception {
    listener.charmSelected(CHARM_ID);
    assertThat(charmModel.isExperienceLearned(CHARM_ID), is(true));
  }

  @Test
  public void forgetsExperienceLearnedCharmOnSelection() throws Exception {
    charmModel.toggleExperiencedLearned(CHARM_ID);
    listener.charmSelected(CHARM_ID);
    assertThat(charmModel.isLearned(CHARM_ID), is(false));
  }

  @Test
  public void doesNotForgetCreationLearnedCharmOnSelection() throws Exception {
    charmModel.toggleCreationLearned(CHARM_ID);
    listener.charmSelected(CHARM_ID);
    assertThat(charmModel.isCreationLearned(CHARM_ID), is(true));
  }
}