package net.sf.anathema.charms.character.learn;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.character.fake.DummyCharmPreferences;
import net.sf.anathema.charms.character.fake.DummyVirtualCharms;
import net.sf.anathema.charms.character.modify.CharmLearner;
import net.sf.anathema.charms.character.preference.ExperienceCharmTreatment;
import net.sf.anathema.charms.character.test.AbstractCharmCharacterTest;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Before;
import org.junit.Test;

public class CharmLearner_CreationTest extends AbstractCharmCharacterTest {

  public static final ICharmId CHARM_ID = new CharmId("charmId", null); //$NON-NLS-1$
  private DummyCharmPreferences preferences;
  private CharmLearner charmLearner;

  @Before
  public void createCreationCharmLearner() {
    getExperience().setExperienced(false);
    preferences = new DummyCharmPreferences();
    charmLearner = new CharmLearner(new DummyVirtualCharms(), preferences, character);
  }

  @Test
  public void learnsCharmByCreationOnSelection() throws Exception {
    charmLearner.learn(CHARM_ID);
    assertThat(getCharmModel().isCreationLearned(CHARM_ID), is(true));
  }

  @Test
  public void forgetsCreationLearnedCharmOnSelection() throws Exception {
    getCharmModel().toggleCreationLearned(CHARM_ID);
    charmLearner.learn(CHARM_ID);
    assertThat(getCharmModel().isLearned(CHARM_ID), is(false));
  }

  @Test
  public void learnsCharmOnCreationEvenIfItIsExperienceLearned() throws Exception {
    preferences.setTreatment(ExperienceCharmTreatment.Forget);
    getCharmModel().toggleExperiencedLearned(CHARM_ID);
    charmLearner.learn(CHARM_ID);
    assertThat(getCharmModel().isCreationLearned(CHARM_ID), is(true));
    assertThat(getCharmModel().isLearned(CHARM_ID), is(true));
  }

  @Test
  public void forgetsExperienceLearnedCharmOnSelectionIfTreatmentForgets() throws Exception {
    preferences.setTreatment(ExperienceCharmTreatment.Forget);
    getCharmModel().toggleExperiencedLearned(CHARM_ID);
    charmLearner.learn(CHARM_ID);
    charmLearner.learn(CHARM_ID);
    assertThat(getCharmModel().isCreationLearned(CHARM_ID), is(false));
    assertThat(getCharmModel().isExperienceLearned(CHARM_ID), is(false));
    assertThat(getCharmModel().isLearned(CHARM_ID), is(false));
  }

  @Test
  public void doesNotForgetExperienceLearnedCharmOnSelectionIfTreatmentRemembers() throws Exception {
    preferences.setTreatment(ExperienceCharmTreatment.Remember);
    getCharmModel().toggleExperiencedLearned(CHARM_ID);
    charmLearner.learn(CHARM_ID);
    charmLearner.learn(CHARM_ID);
    assertThat(getCharmModel().isCreationLearned(CHARM_ID), is(false));
    assertThat(getCharmModel().isExperienceLearned(CHARM_ID), is(true));
    assertThat(getCharmModel().isLearned(CHARM_ID), is(true));
  }
}