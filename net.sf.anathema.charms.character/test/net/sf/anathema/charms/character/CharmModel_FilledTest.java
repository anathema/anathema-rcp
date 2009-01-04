package net.sf.anathema.charms.character;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.character.model.CharmModel;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Before;
import org.junit.Test;

public class CharmModel_FilledTest {

  private static final ICharmId CREATION_CHARM_ID = new CharmId("creation", null); //$NON-NLS-1$
  private static final ICharmId EXPERIENCE_CHARM_ID = new CharmId("experience", null); //$NON-NLS-1$
  private CharmModel charmModel;

  @Before
  public void createModel() throws Exception {
    charmModel = new CharmModel();
    charmModel.toggleCreationLearned(CREATION_CHARM_ID);
    charmModel.toggleExperiencedLearned(EXPERIENCE_CHARM_ID);
  }

  @Test
  public void hasLearnedCreationCharm() throws Exception {
    assertThat(charmModel.isLearned(CREATION_CHARM_ID), is(true));
  }

  @Test
  public void hasLearnedExperienceCharm() throws Exception {
    assertThat(charmModel.isLearned(EXPERIENCE_CHARM_ID), is(true));
  }

  @Test
  public void hasLearnedCreationCharmOnCreation() throws Exception {
    assertThat(charmModel.isCreationLearned(CREATION_CHARM_ID), is(true));
  }

  @Test
  public void hasNotLearnedExperienceCharmOnCreation() throws Exception {
    assertThat(charmModel.isCreationLearned(EXPERIENCE_CHARM_ID), is(false));
  }

  @Test
  public void hasNotLearnedCreationCharmOnExperience() throws Exception {
    assertThat(charmModel.isExperienceLearned(CREATION_CHARM_ID), is(false));
  }

  @Test
  public void hasLearnedExperienceCharmOnExperience() throws Exception {
    assertThat(charmModel.isExperienceLearned(EXPERIENCE_CHARM_ID), is(true));
  }

  @Test
  public void doesNotForgetCreationCharmOnToggleExperienced() throws Exception {
    charmModel.toggleExperiencedLearned(CREATION_CHARM_ID);
    assertThat(charmModel.isLearned(CREATION_CHARM_ID), is(true));
  }

  @Test
  public void doesNotForgetExperienceCharmOnToggleCreation() throws Exception {
    charmModel.toggleCreationLearned(EXPERIENCE_CHARM_ID);
    assertThat(charmModel.isLearned(EXPERIENCE_CHARM_ID), is(true));
  }

  @Test
  public void forgetsCreationLearnedCharmOnToggleCreationLearned() throws Exception {
    charmModel.toggleCreationLearned(CREATION_CHARM_ID);
    assertThat(charmModel.isCreationLearned(CREATION_CHARM_ID), is(false));
  }
}