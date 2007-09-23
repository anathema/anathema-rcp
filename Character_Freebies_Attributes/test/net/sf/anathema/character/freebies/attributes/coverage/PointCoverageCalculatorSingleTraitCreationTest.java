package net.sf.anathema.character.freebies.attributes.coverage;

import static org.junit.Assert.*;
import net.sf.anathema.character.attributes.model.AttributeContextObjectMother;
import net.sf.anathema.character.freebies.attributes.coverage.ICoverageCalculation;
import net.sf.anathema.character.freebies.attributes.coverage.PointCoverageCalculator;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class PointCoverageCalculatorSingleTraitCreationTest {

  private ITraitCollectionContext context;
  private Identificate identificate;
  private TraitGroup traitGroup;

  @Before
  public void createCalculator() throws Exception {
    identificate = new Identificate("Strength"); //$NON-NLS-1$
    traitGroup = new TraitGroup("group", identificate.getId()); //$NON-NLS-1$
    context = AttributeContextObjectMother.createContext(traitGroup);
    context.getCollection().getTrait(identificate.getId()).getCreationModel().setValue(3);
  }

  @Test
  public void indicatesNoOverflowPointsOnExperience() throws Exception {
    context.getExperience().setExperienced(true);
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 0);
    ICoverageCalculation calculation = pointCoverageCalculator.calculateCoverageFor(traitGroup);
    assertEquals(0, calculation.getPointsNotCovered(identificate));
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
  public void indicatesFullCoverageOnExperience() throws Exception {
    context.getExperience().setExperienced(true);
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 0);
    ICoverageCalculation calculation = pointCoverageCalculator.calculateCoverageFor(traitGroup);
    assertEquals(5, calculation.getPointsCovered(identificate));
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
}