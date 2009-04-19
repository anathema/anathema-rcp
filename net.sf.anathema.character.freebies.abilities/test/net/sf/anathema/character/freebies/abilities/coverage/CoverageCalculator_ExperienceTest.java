package net.sf.anathema.character.freebies.abilities.coverage;

import static org.junit.Assert.*;
import net.sf.anathema.character.core.fake.DummyModelCollection;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.fake.DummyTraitCollection;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class CoverageCalculator_ExperienceTest {

  private static final Identificate FIRST = new Identificate("first"); //$NON-NLS-1$
  private DummyTraitCollection collection;

  @Before
  public void createCollection() throws Exception {
    collection = new DummyTraitCollection();
    collection.addTrait(new BasicTrait(FIRST));
  }

  @Test
  public void returnsCreationValueAsCoverage() throws Exception {
    CoverageCalculator coverageCalculator = createCollector();
    IBasicTrait trait = collection.getTrait(FIRST.getId());
    trait.getCreationModel().setValue(2);
    assertEquals(2, coverageCalculator.calculate(collection, null, FIRST));
  }

  private CoverageCalculator createCollector() {
    DummyModelCollection modelCollection = new DummyModelCollection();
    modelCollection.addModel(IExperience.MODEL_ID, new DummyExperience(true));
    return new CoverageCalculator(null, modelCollection);
  }
}