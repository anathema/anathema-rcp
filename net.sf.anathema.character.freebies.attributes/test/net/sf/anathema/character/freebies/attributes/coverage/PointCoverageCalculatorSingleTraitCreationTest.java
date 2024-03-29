package net.sf.anathema.character.freebies.attributes.coverage;

import static org.junit.Assert.*;
import net.sf.anathema.character.attributes.model.AttributeContextObjectMother;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class PointCoverageCalculatorSingleTraitCreationTest {

  private static final int CREATION_VALUE = 3;
  private ITraitCollectionContext context;
  private Identificate identificate;
  private TraitGroup traitGroup;

  @Before
  public void createCalculator() throws Exception {
    identificate = new Identificate("Strength"); //$NON-NLS-1$
    traitGroup = new TraitGroup("group", null, identificate.getId()); //$NON-NLS-1$
    context = AttributeContextObjectMother.createContext(traitGroup);
    context.getCollection().getTrait(identificate.getId()).getCreationModel().setValue(CREATION_VALUE);
  }

  @Test
  public void indicatesNoOverflowPointsOnExperience() throws Exception {
    setExperienced();
    context.getCollection().getTrait(identificate.getId()).getExperiencedModel().setValue(CREATION_VALUE + 2);
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 0);
    ICoverageCalculation calculation = pointCoverageCalculator.calculateCoverageFor(traitGroup);
    assertEquals(2, calculation.getPointsNotCovered(identificate));
  }

  @Test
  public void returnsSpentValueAsOverflow() throws Exception {
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 0);
    ICoverageCalculation calculation = pointCoverageCalculator.calculateCoverageFor(traitGroup);
    assertEquals(2, calculation.getPointsNotCovered(identificate));
  }

  @Test
  public void returnsExcessValueAsOverflow() throws Exception {
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 1);
    ICoverageCalculation calculation = pointCoverageCalculator.calculateCoverageFor(traitGroup);
    assertEquals(1, calculation.getPointsNotCovered(identificate));
  }

  @Test
  public void doesNotReturnNegativeValuesForUncoveredPoints() throws Exception {
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 3);
    ICoverageCalculation calculation = pointCoverageCalculator.calculateCoverageFor(traitGroup);
    assertEquals(0, calculation.getPointsNotCovered(identificate));
  }

  @Test
  public void indicatesCreationValueCoverageOnExperience() throws Exception {
    setExperienced();
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 0);
    ICoverageCalculation calculation = pointCoverageCalculator.calculateCoverageFor(traitGroup);
    assertEquals(CREATION_VALUE, calculation.getPointsCovered(identificate));
  }

  @Test
  public void respectsLackOfCreditWhenCalculatingCoverage() throws Exception {
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 0);
    ICoverageCalculation calculation = pointCoverageCalculator.calculateCoverageFor(traitGroup);
    assertEquals(1, calculation.getPointsCovered(identificate));
  }

  @Test
  public void usesCreditFully() throws Exception {
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 1);
    ICoverageCalculation calculation = pointCoverageCalculator.calculateCoverageFor(traitGroup);
    assertEquals(2, calculation.getPointsCovered(identificate));
  }

  @Test
  public void doesCoverMorePointsThanRequired() throws Exception {
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 3);
    ICoverageCalculation calculation = pointCoverageCalculator.calculateCoverageFor(traitGroup);
    assertEquals(3, calculation.getPointsCovered(identificate));
  }

  private void setExperienced() {
    context.getExperience().setExperienced(true);
  }
}