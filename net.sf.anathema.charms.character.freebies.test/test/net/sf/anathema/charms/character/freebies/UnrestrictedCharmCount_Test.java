package net.sf.anathema.charms.character.freebies;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.character.model.CharmModel;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Before;
import org.junit.Test;

public class UnrestrictedCharmCount_Test {
  private static final ICharmId FIRST_CHARM = new CharmId("firstCharm", null); //$NON-NLS-1$
  private static final ICharmId SECOND_CHARM = new CharmId("secondCharm", null); //$NON-NLS-1$
  private static final ICharmId EXPERIENCE_CHARM = new CharmId("experienceCharm", null); //$NON-NLS-1$
  private ICharmModel charms;
  private UnrestrictedCharmCount count;

  @Before
  public void createCharms() {
    charms = new CharmModel();
    count = new UnrestrictedCharmCount(charms);
  }

  @Test
  public void doesCountOneCreationLearnedCharm() throws Exception {
    charms.toggleCreationLearned(FIRST_CHARM);
    assertThat(count.count(), is(1));
  }

  @Test
  public void doesCountMultipleCreationLearnedCharms() throws Exception {
    charms.toggleCreationLearned(FIRST_CHARM);
    charms.toggleCreationLearned(SECOND_CHARM);
    assertThat(count.count(), is(2));
  }

  @Test
  public void doesNotCountCharmsLearnedOnExperience() throws Exception {
    charms.toggleExperiencedLearned(EXPERIENCE_CHARM);
    assertThat(count.count(), is(0));
  }
}