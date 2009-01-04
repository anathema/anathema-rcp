package net.sf.anathema.charms.character.freebies;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.charms.character.model.CharmModel;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Before;
import org.junit.Test;

public class CheapCharmCount_Test {

  private static final ICharmId FIRST_CHARM = new CharmId("firstCharm", null); //$NON-NLS-1$
  private static final ICharmId SECOND_CHARM = new CharmId("secondCharm", null); //$NON-NLS-1$
  private static final ICharmId EXPERIENCE_CHARM = new CharmId("experienceCharm", null); //$NON-NLS-1$
  private ICharmModel charms;

  @Before
  public void createCharms() {
    charms = new CharmModel();
    charms.toggleCreationLearned(FIRST_CHARM);
    charms.toggleCreationLearned(SECOND_CHARM);
    charms.toggleExperiencedLearned(EXPERIENCE_CHARM);
  }

  @Test
  public void countsZeroWithoutCheapCharms() throws Exception {
    IPredicate<ICharmId> cheapPredicate = PredicateObjectMother.create();
    ICount charmCount = new CheapCharmCount(charms, cheapPredicate);
    assertThat(charmCount.count(), is(0));
  }

  @Test
  public void countsOneFavoredCharm() throws Exception {
    IPredicate<ICharmId> cheapPredicate = PredicateObjectMother.create(FIRST_CHARM);
    ICount charmCount = new CheapCharmCount(charms, cheapPredicate);
    assertThat(charmCount.count(), is(1));
  }

  @Test
  public void countsMultipleFavoredCharms() throws Exception {
    IPredicate<ICharmId> cheapPredicate = PredicateObjectMother.create(FIRST_CHARM, SECOND_CHARM);
    ICount charmCount = new CheapCharmCount(charms, cheapPredicate);
    assertThat(charmCount.count(), is(2));
  }

  @Test
  public void doesNotCountCharmsLearnedByExperience() throws Exception {
    IPredicate<ICharmId> cheapPredicate = PredicateObjectMother.create(EXPERIENCE_CHARM);
    ICount charmCount = new CheapCharmCount(charms, cheapPredicate);
    assertThat(charmCount.count(), is(0));
  }
}