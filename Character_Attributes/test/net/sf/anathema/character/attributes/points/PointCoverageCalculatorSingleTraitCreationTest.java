package net.sf.anathema.character.attributes.points;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.lib.util.Identificate;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class PointCoverageCalculatorSingleTraitCreationTest {

  private DummyExperience experience;
  private ITraitCollectionContext context;
  private Identificate identificate;

  @Before
  public void createCalculator() throws Exception {
    context = EasyMock.createNiceMock(ITraitCollectionContext.class);
    experience = new DummyExperience();
    DummyTraitCollection collection = createTraitCollection();
    EasyMock.expect(context.getExperience()).andReturn(experience);
    EasyMock.expect(context.getCollection()).andReturn(collection);
    EasyMock.replay(context);
  }

  private DummyTraitCollection createTraitCollection() {
    DummyTraitCollection collection = new DummyTraitCollection();
    identificate = new Identificate("Strength"); //$NON-NLS-1$
    BasicTrait basicTrait = new BasicTrait(identificate);
    basicTrait.getCreationModel().setValue(3);
    collection.addTrait(basicTrait);
    return collection;
  }

  @Test
  public void indicatesNoOverflowPointsOnExperience() throws Exception {
    experience.setExperienced(true);
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 0);
    int result = pointCoverageCalculator.calculate(identificate);
    assertEquals(0, result);
  }

  @Test
  public void returnsValueAsOverflow() throws Exception {
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 0);
    int result = pointCoverageCalculator.calculate(identificate);
    assertEquals(3, result);
  }

  @Test
  public void returnsExcessValueAsOverflow() throws Exception {
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 2);
    int result = pointCoverageCalculator.calculate(identificate);
    assertEquals(1, result);
  }
}