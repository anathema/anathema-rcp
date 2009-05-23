package net.sf.anathema.charms.character;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.core.fake.DummyCharacter;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.charms.character.model.CharmCharacter;
import net.sf.anathema.charms.character.model.CharmModel;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.character.preference.ExperienceCharmTreatment;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Before;
import org.junit.Test;

public class LearningCharmSelectionListener_CreationTest {

  public static final ICharmId CHARM_ID = new CharmId("charmId", null); //$NON-NLS-1$
  private LearningCharmSelectionListener listener;
  private DummyCharmPreferences preferences;
  private DummyCharacter dummyCharacter;

  @Before
  public void createsCharmModel() {
    dummyCharacter = new DummyCharacter();
    preferences = new DummyCharmPreferences();
    dummyCharacter.modelsById.put(ICharmModel.MODEL_ID, new CharmModel());
    dummyCharacter.modelsById.put(IExperience.MODEL_ID, new DummyExperience(false));
    CharmCharacter charmCharacter = new CharmCharacter(new DummyVirtualCharms(), dummyCharacter);
    listener = new LearningCharmSelectionListener(getCharmModel(), getExperience(), preferences, charmCharacter);
  }

  private CharmModel getCharmModel() {
    return (CharmModel) dummyCharacter.getModel(ICharmModel.MODEL_ID);
  }

  private IExperience getExperience() {
    return (IExperience) dummyCharacter.getModel(IExperience.MODEL_ID);
  }

  @Test
  public void learnsCharmByCreationOnSelection() throws Exception {
    listener.charmSelected(CHARM_ID);
    assertThat(getCharmModel().isCreationLearned(CHARM_ID), is(true));
  }

  @Test
  public void forgetsCreationLearnedCharmOnSelection() throws Exception {
    getCharmModel().toggleCreationLearned(CHARM_ID);
    listener.charmSelected(CHARM_ID);
    assertThat(getCharmModel().isLearned(CHARM_ID), is(false));
  }

  @Test
  public void learnsCharmOnCreationEvenIfItIsExperienceLearned() throws Exception {
    preferences.setTreatment(ExperienceCharmTreatment.Forget);
    getCharmModel().toggleExperiencedLearned(CHARM_ID);
    listener.charmSelected(CHARM_ID);
    assertThat(getCharmModel().isCreationLearned(CHARM_ID), is(true));
    assertThat(getCharmModel().isLearned(CHARM_ID), is(true));
  }

  @Test
  public void forgetsExperienceLearnedCharmOnSelectionIfTreatmentForgets() throws Exception {
    preferences.setTreatment(ExperienceCharmTreatment.Forget);
    getCharmModel().toggleExperiencedLearned(CHARM_ID);
    listener.charmSelected(CHARM_ID);
    listener.charmSelected(CHARM_ID);
    assertThat(getCharmModel().isCreationLearned(CHARM_ID), is(false));
    assertThat(getCharmModel().isExperienceLearned(CHARM_ID), is(false));
    assertThat(getCharmModel().isLearned(CHARM_ID), is(false));
  }

  @Test
  public void doesNotForgetExperienceLearnedCharmOnSelectionIfTreatmentRemembers() throws Exception {
    preferences.setTreatment(ExperienceCharmTreatment.Remember);
    getCharmModel().toggleExperiencedLearned(CHARM_ID);
    listener.charmSelected(CHARM_ID);
    listener.charmSelected(CHARM_ID);
    assertThat(getCharmModel().isCreationLearned(CHARM_ID), is(false));
    assertThat(getCharmModel().isExperienceLearned(CHARM_ID), is(true));
    assertThat(getCharmModel().isLearned(CHARM_ID), is(true));
  }
}