package net.sf.anathema.character.freebies.abilities.coverage;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.core.fake.DummyModelCollection;
import net.sf.anathema.character.experience.Experience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.freebies.abilities.util.IAbilityFreebiesConstants;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.fake.DummyTraitCollection;
import net.sf.anathema.character.trait.status.FavoredStatus;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class CoverageCalculator_CreationTest {

  private static final Identificate FIRST = new Identificate("first"); //$NON-NLS-1$
  private static final Identificate SECOND = new Identificate("second"); //$NON-NLS-1$
  private static final Identificate THIRD = new Identificate("third"); //$NON-NLS-1$
  private DummyTraitCollection collection;
  private DummyModelCollection modelCollection;

  @Before
  public void createCollection() throws Exception {
    collection = new DummyTraitCollection();
    collection.addTrait(new BasicTrait(FIRST));
    collection.addTrait(new BasicTrait(SECOND));
    collection.addTrait(new BasicTrait(THIRD));
    modelCollection = new DummyModelCollection();
    modelCollection.addModel(IExperience.MODEL_ID, Experience.Create(false));
  }

  @Test
  public void allPointsOfFirstFavoredAreCoveredByFavoredCredit() throws Exception {
    setFirstFavoredWithValue(2);
    ICreditManager creditManager = createCreditManager(2, 3);
    CoverageCalculator coverageCalculator = createCollector(creditManager);
    assertEquals(2, coverageCalculator.calculate(collection, null, FIRST));
  }

  @Test
  public void favoredDotsAboveThreeAreNotCovered() throws Exception {
    setFirstFavoredWithValue(4);
    ICreditManager creditManager = createCreditManager(3, 4);
    CoverageCalculator coverageCalculator = createCollector(creditManager);
    assertEquals(3, coverageCalculator.calculate(collection, null, FIRST));
  }

  @Test
  public void favoredDotsAboveFavoredAreNotCoveredWithoutFreeUnlimitedDots() throws Exception {
    setFirstFavoredWithValue(3);
    ICreditManager creditManager = createCreditManager(2, 0);
    CoverageCalculator coverageCalculator = createCollector(creditManager);
    assertEquals(2, coverageCalculator.calculate(collection, null, FIRST));
  }

  @Test
  public void favoredDotsAreCoveredWithFreeUnlimitedDots() throws Exception {
    setFirstFavoredWithValue(3);
    ICreditManager creditManager = createCreditManager(2, 1);
    CoverageCalculator coverageCalculator = createCollector(creditManager);
    assertEquals(3, coverageCalculator.calculate(collection, null, FIRST));
  }

  private void setFirstFavoredWithValue(int value) {
    IBasicTrait first = collection.getTrait(FIRST.getId());
    first.getStatusManager().setStatus(new FavoredStatus());
    first.getCreationModel().setValue(value);
  }

  private ICreditManager createCreditManager(int favored, int unlimited) {
    ICreditManager creditManager = createMock(ICreditManager.class);
    expect(creditManager.getCredit(null, IAbilityFreebiesConstants.FAVORED_CREDIT)).andReturn(favored);
    expect(creditManager.getCredit(null, IAbilityFreebiesConstants.UNRESTRICTED_CREDIT)).andReturn(unlimited);
    replay(creditManager);
    return creditManager;
  }

  @Test
  public void generalDotsAboveThreeAreNotCovered() throws Exception {
    collection.getTrait(FIRST.getId()).getCreationModel().setValue(4);
    ICreditManager creditManager = createCreditManager(3, 4);
    CoverageCalculator coverageCalculator = createCollector(creditManager);
    assertEquals(3, coverageCalculator.calculate(collection, null, FIRST));
  }

  @Test
  public void generalDotsAreNotCoveredByFavoredCredit() throws Exception {
    collection.getTrait(FIRST.getId()).getCreationModel().setValue(3);
    ICreditManager creditManager = createCreditManager(2, 0);
    CoverageCalculator coverageCalculator = createCollector(creditManager);
    assertEquals(0, coverageCalculator.calculate(collection, null, FIRST));
  }

  private CoverageCalculator createCollector(ICreditManager creditManager) {
    return new CoverageCalculator(creditManager, modelCollection);
  }
}