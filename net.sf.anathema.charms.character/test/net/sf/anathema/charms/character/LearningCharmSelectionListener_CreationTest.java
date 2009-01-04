package net.sf.anathema.charms.character;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.charms.character.model.CharmModel;
import net.sf.anathema.charms.character.preference.ExperienceCharmTreatment;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Before;
import org.junit.Test;

public class LearningCharmSelectionListener_CreationTest {

  public static final ICharmId CHARM_ID = new CharmId("charmId", null); //$NON-NLS-1$
  private CharmModel charmModel;
  private LearningCharmSelectionListener listener;
  private DummyCharmPreferences preferences;

  @Before
  public void createsCharmModel() {
    charmModel = new CharmModel();
    preferences = new DummyCharmPreferences();
    listener = new LearningCharmSelectionListener(charmModel, new DummyExperience(false), preferences);
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
  public void learnsCharmOnCreationEvenIfItIsExperienceLearned() throws Exception {
    preferences.setTreatment(ExperienceCharmTreatment.Forget);
    charmModel.toggleExperiencedLearned(CHARM_ID);
    listener.charmSelected(CHARM_ID);
    assertThat(charmModel.isCreationLearned(CHARM_ID), is(true));
    assertThat(charmModel.isLearned(CHARM_ID), is(true));
  }

  @Test
  public void forgetsExperienceLearnedCharmOnSelectionIfTreatmentForgets() throws Exception {
    preferences.setTreatment(ExperienceCharmTreatment.Forget);
    charmModel.toggleExperiencedLearned(CHARM_ID);
    listener.charmSelected(CHARM_ID);
    listener.charmSelected(CHARM_ID);
    assertThat(charmModel.isCreationLearned(CHARM_ID), is(false));
    assertThat(charmModel.isExperienceLearned(CHARM_ID), is(false));
    assertThat(charmModel.isLearned(CHARM_ID), is(false));
  }

  @Test
  public void doesNotForgetExperienceLearnedCharmOnSelectionIfTreatmentRemembers() throws Exception {
    preferences.setTreatment(ExperienceCharmTreatment.Remember);
    charmModel.toggleExperiencedLearned(CHARM_ID);
    listener.charmSelected(CHARM_ID);
    listener.charmSelected(CHARM_ID);
    assertThat(charmModel.isCreationLearned(CHARM_ID), is(false));
    assertThat(charmModel.isExperienceLearned(CHARM_ID), is(true));
    assertThat(charmModel.isLearned(CHARM_ID), is(true));
  }
}