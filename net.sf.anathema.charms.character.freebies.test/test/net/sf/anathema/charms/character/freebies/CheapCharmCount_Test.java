package net.sf.anathema.charms.character.freebies;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.charms.character.model.CharmModel;
import net.sf.anathema.charms.character.model.ICharmModel;

import org.junit.Before;
import org.junit.Test;

public class CheapCharmCount_Test {

  private static final String FIRST_CHARM = "firstCharm"; //$NON-NLS-1$
  private static final String SECOND_CHARM = "secondCharm"; //$NON-NLS-1$
  private ICharmModel charms;

  @Before
  public void createCharms() {
    charms = new CharmModel();
    charms.toggleCreationLearned(FIRST_CHARM);
    charms.toggleCreationLearned(SECOND_CHARM);
  }

  @Test
  public void countsZeroWithoutCheapCharms() throws Exception {
    IPredicate<String> cheapPredicate = PredicateObjectMother.create();
    ICount charmCount = new CheapCharmCount(charms, cheapPredicate);
    assertThat(charmCount.count(), is(0));
  }

  @Test
  public void countsOneFavoredCharm() throws Exception {
    IPredicate<String> cheapPredicate = PredicateObjectMother.create(FIRST_CHARM);
    ICount charmCount = new CheapCharmCount(charms, cheapPredicate);
    assertThat(charmCount.count(), is(1));
  }

  @Test
  public void countsMultipleFavoredCharms() throws Exception {
    IPredicate<String> cheapPredicate = PredicateObjectMother.create(FIRST_CHARM, SECOND_CHARM);
    ICount charmCount = new CheapCharmCount(charms, cheapPredicate);
    assertThat(charmCount.count(), is(2));
  }
}