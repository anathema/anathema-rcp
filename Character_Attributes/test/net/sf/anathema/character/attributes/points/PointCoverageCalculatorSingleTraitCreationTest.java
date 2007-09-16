package net.sf.anathema.character.attributes.points;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.attributes.TraitCollectionObjectMother;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.group.TraitGroup;
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
    identificate = new Identificate("Strength"); //$NON-NLS-1$
    DummyTraitCollection collection = TraitCollectionObjectMother.createTraitCollection(identificate);
    collection.getTrait(identificate.getId()).getCreationModel().setValue(3);
    EasyMock.expect(context.getExperience()).andReturn(experience);
    EasyMock.expect(context.getCollection()).andReturn(collection);
    EasyMock.replay(context);
  }

  @Test
  public void indicatesNoOverflowPointsOnExperience() throws Exception {
    experience.setExperienced(true);
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 0);
    pointCoverageCalculator.calculateFor(new TraitGroup("group", identificate.getId())); //$NON-NLS-1$
    assertEquals(0, pointCoverageCalculator.getUncoveredPoints(identificate));
  }

  @Test
  public void returnsSpentValueAsOverflow() throws Exception {
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 0);
    pointCoverageCalculator.calculateFor(new TraitGroup("group", identificate.getId())); //$NON-NLS-1$
    assertEquals(2, pointCoverageCalculator.getUncoveredPoints(identificate));
  }

  @Test
  public void returnsExcessValueAsOverflow() throws Exception {
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 1);
    pointCoverageCalculator.calculateFor(new TraitGroup("group", identificate.getId())); //$NON-NLS-1$
    assertEquals(1, pointCoverageCalculator.getUncoveredPoints(identificate));
  }
  
  @Test
  public void doesNotReturnNegativeValues() throws Exception {
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 3);
    pointCoverageCalculator.calculateFor(new TraitGroup("group", identificate.getId())); //$NON-NLS-1$
    assertEquals(0, pointCoverageCalculator.getUncoveredPoints(identificate));
  }
}