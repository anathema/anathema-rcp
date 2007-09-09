package net.sf.anathema.character.attributes.points;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.lib.util.Identificate;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class PointCoverageCalculatorExperienceTest {

  private PointCoverageCalculator pointCoverageCalculator;

  @Before
  public void createCalculator() throws Exception {
    ITraitCollectionContext context = EasyMock.createMock(ITraitCollectionContext.class);
    DummyExperience experience = new DummyExperience();
    experience.setExperienced(true);
    EasyMock.expect(context.getExperience()).andReturn(experience);
    pointCoverageCalculator = new PointCoverageCalculator(context);
  }

  @Test
  public void indicatesNoOverflowPointsOnExperience() throws Exception {
    int result = pointCoverageCalculator.calculate(new Identificate("Strength")); //$NON-NLS-1$
    assertEquals(0, result);
  }
}