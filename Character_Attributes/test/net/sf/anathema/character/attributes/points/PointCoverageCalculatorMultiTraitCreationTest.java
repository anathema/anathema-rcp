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

public class PointCoverageCalculatorMultiTraitCreationTest {

  private DummyExperience experience;
  private ITraitCollectionContext context;
  private Identificate firstTraitId;
  private Identificate secondTraitId;
  private DummyTraitCollection collection;

  @Before
  public void createCalculator() throws Exception {
    context = EasyMock.createNiceMock(ITraitCollectionContext.class);
    experience = new DummyExperience();
    firstTraitId = new Identificate("First"); //$NON-NLS-1$
    secondTraitId = new Identificate("Second"); //$NON-NLS-1$
    collection = TraitCollectionObjectMother.createTraitCollection(firstTraitId, secondTraitId);
    collection.getTrait(firstTraitId.getId()).getCreationModel().setValue(3);
    collection.getTrait(secondTraitId.getId()).getCreationModel().setValue(2);
    EasyMock.expect(context.getExperience()).andReturn(experience).anyTimes();
    EasyMock.expect(context.getCollection()).andReturn(collection).anyTimes();
    EasyMock.replay(context);
  }

  @Test
  public void returnsGroupExcessValueAsOverflow() throws Exception {
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 2);
    ICoverageCalculation calculation = pointCoverageCalculator.calculateCoverageFor(new TraitGroup(
        "group", firstTraitId.getId(), secondTraitId.getId())); //$NON-NLS-1$
    assertEquals(1, calculation.getPointsNotCovered(secondTraitId));
  }

  @Test
  public void returnsGroupExcessValueAsOverflowOnlyOnce() throws Exception {
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 2);
    ICoverageCalculation calculation = pointCoverageCalculator.calculateCoverageFor(new TraitGroup(
        "group", firstTraitId.getId(), secondTraitId.getId())); //$NON-NLS-1$
    assertEquals(0, calculation.getPointsNotCovered(firstTraitId));
  }

  @Test
  public void favorsFavoredTraits() throws Exception {
    collection.getTrait(secondTraitId.getId()).getFavoredModel().setValue(true);
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 1);
    ICoverageCalculation calculation = pointCoverageCalculator.calculateCoverageFor(new TraitGroup(
        "group", firstTraitId.getId(), secondTraitId.getId())); //$NON-NLS-1$
    assertEquals(2, calculation.getPointsNotCovered(firstTraitId));
  }
}