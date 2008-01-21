package net.sf.anathema.character.freebies.attributes.coverage;

import static org.junit.Assert.*;
import net.sf.anathema.character.attributes.model.AttributeContextObjectMother;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.status.FavoredStatus;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class PointCoverageCalculatorMultiTraitCreationTest {

  private ITraitCollectionContext context;
  private Identificate firstTraitId;
  private Identificate secondTraitId;
  private ITraitCollectionModel collection;
  private TraitGroup traitGroup;

  @Before
  public void createCalculator() throws Exception {
    firstTraitId = new Identificate("First"); //$NON-NLS-1$
    secondTraitId = new Identificate("Second"); //$NON-NLS-1$
    traitGroup = new TraitGroup("group", firstTraitId.getId(), secondTraitId.getId()); //$NON-NLS-1$
    context = AttributeContextObjectMother.createContext(traitGroup);
    collection = context.getCollection();
    collection.getTrait(firstTraitId.getId()).getCreationModel().setValue(3);
    collection.getTrait(secondTraitId.getId()).getCreationModel().setValue(2);
  }

  @Test
  public void returnsGroupExcessValueAsOverflow() throws Exception {
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 2);
    ICoverageCalculation calculation = pointCoverageCalculator.calculateCoverageFor(traitGroup);
    assertEquals(1, calculation.getPointsNotCovered(secondTraitId));
  }

  @Test
  public void returnsGroupExcessValueAsOverflowOnlyOnce() throws Exception {
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 2);
    ICoverageCalculation calculation = pointCoverageCalculator.calculateCoverageFor(traitGroup);
    assertEquals(0, calculation.getPointsNotCovered(firstTraitId));
  }

  @Test
  public void favorsFavoredTraits() throws Exception {
    collection.getTrait(secondTraitId.getId()).getStatusManager().setStatus(new FavoredStatus());
    PointCoverageCalculator pointCoverageCalculator = new PointCoverageCalculator(context, 1);
    ICoverageCalculation calculation = pointCoverageCalculator.calculateCoverageFor(traitGroup);
    assertEquals(2, calculation.getPointsNotCovered(firstTraitId));
  }
}