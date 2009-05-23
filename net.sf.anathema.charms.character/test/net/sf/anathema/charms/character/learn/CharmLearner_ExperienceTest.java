package net.sf.anathema.charms.character.learn;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.character.fake.DummyCharmPreferences;
import net.sf.anathema.charms.character.fake.DummyVirtualCharms;
import net.sf.anathema.charms.character.modify.CharmLearner;
import net.sf.anathema.charms.character.test.AbstractCharmCharacterTest;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Before;
import org.junit.Test;

public class CharmLearner_ExperienceTest extends AbstractCharmCharacterTest {

  public static final ICharmId CHARM_ID = new CharmId("charmId", null); //$NON-NLS-1$
  private CharmLearner charmCharacter;

  @Before
  public void createExperienceCharmLearner() {
    getExperience().setExperienced(true);
    DummyCharmPreferences preferences = new DummyCharmPreferences();
    DummyVirtualCharms virtualCharms = new DummyVirtualCharms();
    charmCharacter = new CharmLearner(virtualCharms, preferences, character);
  }

  @Test
  public void learnsUnknownCharmByExperienceOnSelection() throws Exception {
    charmCharacter.learn(CHARM_ID);
    assertThat(getCharmModel().isExperienceLearned(CHARM_ID), is(true));
  }

  @Test
  public void forgetsExperienceLearnedCharmOnSelection() throws Exception {
    getCharmModel().toggleExperiencedLearned(CHARM_ID);
    charmCharacter.learn(CHARM_ID);
    assertThat(getCharmModel().isLearned(CHARM_ID), is(false));
  }

  @Test
  public void doesNotForgetCreationLearnedCharmOnSelection() throws Exception {
    getCharmModel().toggleCreationLearned(CHARM_ID);
    charmCharacter.learn(CHARM_ID);
    assertThat(getCharmModel().isCreationLearned(CHARM_ID), is(true));
  }
}