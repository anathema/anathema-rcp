package net.sf.anathema.charms.character;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.validator.DummyCharacter;
import net.sf.anathema.charms.character.model.CharmCharacter;
import net.sf.anathema.charms.character.model.CharmModel;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Before;
import org.junit.Test;

public class LearningCharmSelectionListener_ExperienceTest {

  public static final ICharmId CHARM_ID = new CharmId("charmId", null); //$NON-NLS-1$
  private CharmModel charmModel;
  private LearningCharmSelectionListener listener;
  private DummyCharacter dummyCharacter;

  @Before
  public void createsCharmModel() {
    DummyCharmPreferences preferences = new DummyCharmPreferences();
    dummyCharacter = new DummyCharacter();
    dummyCharacter.modelsById.put(ICharmModel.MODEL_ID, new CharmModel());
    dummyCharacter.modelsById.put(IExperience.MODEL_ID, new DummyExperience(true));
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
  public void learnsUnknownCharmByExperienceOnSelection() throws Exception {
    listener.charmSelected(CHARM_ID);
    assertThat(getCharmModel().isExperienceLearned(CHARM_ID), is(true));
  }

  @Test
  public void forgetsExperienceLearnedCharmOnSelection() throws Exception {
    getCharmModel().toggleExperiencedLearned(CHARM_ID);
    listener.charmSelected(CHARM_ID);
    assertThat(getCharmModel().isLearned(CHARM_ID), is(false));
  }

  @Test
  public void doesNotForgetCreationLearnedCharmOnSelection() throws Exception {
    getCharmModel().toggleCreationLearned(CHARM_ID);
    listener.charmSelected(CHARM_ID);
    assertThat(getCharmModel().isCreationLearned(CHARM_ID), is(true));
  }
}