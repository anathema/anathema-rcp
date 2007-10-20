package net.sf.anathema.character.points.problems;

import static org.junit.Assert.*;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.experience.IExperience;

import org.junit.Before;
import org.junit.Test;

public class BonusPointProviderTest {

  private DummyExperience dummyExperience;
  private DummyModelCollection modelCollection;
  private BonusPointsProvider bonusPointsProvider;

  @Before
  public void createProvider() throws Exception {
    modelCollection = new DummyModelCollection();
    dummyExperience = new DummyExperience();
    modelCollection.addModel(IExperience.MODEL_ID, dummyExperience);
    bonusPointsProvider = new BonusPointsProvider(modelCollection);
  }
  
  @Test
  public void zeroBonusPointsAvailableOnExperienced() throws Exception {
    dummyExperience.setExperienced(true);
    assertEquals(0, bonusPointsProvider.getAvailableBonusPoints(null));
  }
}