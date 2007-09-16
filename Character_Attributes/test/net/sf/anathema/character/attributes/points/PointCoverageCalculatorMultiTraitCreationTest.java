package net.sf.anathema.character.attributes.points;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.attributes.TraitCollectionObjectMother;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.lib.util.Identificate;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class PointCoverageCalculatorMultiTraitCreationTest {

  private DummyExperience experience;
  private ITraitCollectionContext context;
  private Identificate firstTraitId;
  private Identificate secondTraitId;

  @Before
  public void createCalculator() throws Exception {
    context = EasyMock.createNiceMock(ITraitCollectionContext.class);
    experience = new DummyExperience();
    firstTraitId = new Identificate("First"); //$NON-NLS-1$
    secondTraitId = new Identificate("Second"); //$NON-NLS-1$
    DummyTraitCollection collection = TraitCollectionObjectMother.createTraitCollection(firstTraitId, secondTraitId);
    collection.getTrait(firstTraitId.getId()).getCreationModel().setValue(3);
    collection.getTrait(secondTraitId.getId()).getCreationModel().setValue(2);
    EasyMock.expect(context.getExperience()).andReturn(experience).anyTimes();
    EasyMock.expect(context.getCollection()).andReturn(collection).anyTimes();
    EasyMock.replay(context);
  }

  @Test
  public void returnsGroupExcessValueAsOverflow() throws Exception {
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 3);
    pointCoverageCalculator.calculateFor(firstTraitId, secondTraitId);
    int result = pointCoverageCalculator.pointCoverage(secondTraitId);
    assertEquals(2, result);
  }

  @Test
  public void returnsGroupExcessValueAsOverflowOnlyOnce() throws Exception {
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 3);
    pointCoverageCalculator.calculateFor(firstTraitId, secondTraitId);
    pointCoverageCalculator.pointCoverage(secondTraitId);
    int result = pointCoverageCalculator.pointCoverage(firstTraitId);
    assertEquals(0, result);
  }
}