package net.sf.anathema.character.abilities.points;

import static org.junit.Assert.*;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.fake.DummyTraitCollection;
import net.sf.anathema.character.trait.status.FavoredStatus;

import org.junit.Before;
import org.junit.Test;

public class AblitiesBonusPointHandler_Test {

  private BasicTrait superTrait;
  private DummyTraitCollection traitCollection;
  private AbilitiesBonusPointHandler pointHandler;

  @Before
  public void createCollectionWithOneTrait() {
    superTrait = new BasicTrait("Supertrait"); //$NON-NLS-1$
    traitCollection = new DummyTraitCollection();
    traitCollection.addTrait(superTrait);
  }

  @Before
  public void createPointHandler() {
    pointHandler = new AbilitiesBonusPointHandler(null, null);
  }

  @Test
  public void needsTwoPointPerExpensiveSubTraitDot() throws Exception {
    BasicTrait subTrait = new BasicTrait("Subtrait"); //$NON-NLS-1$
    subTrait.getCreationModel().setValue(2);
    traitCollection.addSubTrait(superTrait.getTraitType().getId(), subTrait);
    assertEquals(4, pointHandler.calculatePoints(traitCollection, null));
  }

  @Test
  public void needsOnePointPerCheapSubTraitDot() throws Exception {
    BasicTrait subTrait = new BasicTrait("Subtrait"); //$NON-NLS-1$
    subTrait.getCreationModel().setValue(2);
    subTrait.getStatusManager().setStatus(new FavoredStatus());
    traitCollection.addSubTrait(superTrait.getTraitType().getId(), subTrait);
    assertEquals(2, pointHandler.calculatePoints(traitCollection, null));
  }

  @Test
  public void needsTwoPointsPerExpensiveNonsubbedTraitDot() throws Exception {
    superTrait.getCreationModel().setValue(2);
    assertEquals(4, pointHandler.calculatePoints(traitCollection, null));
  }

  @Test
  public void needsOnePointsPerCheapNonsubbedTraitDot() throws Exception {
    superTrait.getCreationModel().setValue(2);
    superTrait.getStatusManager().setStatus(new FavoredStatus());
    assertEquals(2, pointHandler.calculatePoints(traitCollection, null));
  }

  @Test
  public void needsNoPointsForSupertraitDots() throws Exception {
    superTrait.getCreationModel().setValue(2);
    BasicTrait subTrait = new BasicTrait("Subtrait"); //$NON-NLS-1$
    traitCollection.addSubTrait(superTrait.getTraitType().getId(), subTrait);
    assertEquals(0, pointHandler.calculatePoints(traitCollection, null));
  }

  @Test
  public void addsUpSubTraitCosts() throws Exception {
    BasicTrait subTrait = new BasicTrait("Subtrait"); //$NON-NLS-1$
    BasicTrait secondSubTrait = new BasicTrait("Subtrait2"); //$NON-NLS-1$
    subTrait.getCreationModel().setValue(2);
    secondSubTrait.getCreationModel().setValue(3);
    traitCollection.addSubTrait(superTrait.getTraitType().getId(), subTrait);
    traitCollection.addSubTrait(superTrait.getTraitType().getId(), secondSubTrait);
    assertEquals(10, pointHandler.calculatePoints(traitCollection, null));
  }
}