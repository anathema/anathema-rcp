package net.sf.anathema.charms.character.evaluation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.character.test.AbstractCharmCharacterTest;
import net.sf.anathema.charms.tree.CharmId;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class HasLearnedCharm_Test extends AbstractCharmCharacterTest {

  private HasLearnedCharm hasLearnedCharm;

  @Before
  public void createHasLearnedCharm() throws Exception {
    hasLearnedCharm = new HasLearnedCharm();
  }

  @Test
  public void hasLearnedCreationLearnedCharmOnCreation() throws Exception {
    getExperience().setExperienced(false);
    getCharmModel().toggleCreationLearned(new CharmId("creationLearned", "trait"));
    assertThat(hasLearnedCharm.has(character, "creationLearned"), is(true));
  }

  @Test
  public void hasLearnedCreationLearnedCharmOnExperience() throws Exception {
    getExperience().setExperienced(true);
    getCharmModel().toggleCreationLearned(new CharmId("creationLearned", "trait"));
    assertThat(hasLearnedCharm.has(character, "creationLearned"), is(true));
  }

  @Test
  public void hasNotLearnedExperienceLearnedCharmOnCreation() throws Exception {
    getExperience().setExperienced(false);
    getCharmModel().toggleExperiencedLearned(new CharmId("experienceLearned", "trait"));
    assertThat(hasLearnedCharm.has(character, "experienceLearned"), is(false));
  }

  @Test
  public void hasLearnedExperienceLearnedCharmOnExperience() throws Exception {
    getExperience().setExperienced(true);
    getCharmModel().toggleExperiencedLearned(new CharmId("experienceLearned", "trait"));
    assertThat(hasLearnedCharm.has(character, "experienceLearned"), is(true));
  }

  @Test
  public void hasNotLearnedUnresolvedPatternCharm() throws Exception {
    getCharmModel().toggleCreationLearned(new CharmId("creationLearned for {0}", "trait"));
    assertThat(hasLearnedCharm.has(character, "creationLearned for {0}"), is(false));
  }

  @Test
  public void hasNotLearnedResolvedPatternCharm() throws Exception {
    getCharmModel().toggleCreationLearned(new CharmId("creationLearned for {0}", "trait"));
    assertThat(hasLearnedCharm.has(character, "creationLearned for trait"), is(true));
  }
}