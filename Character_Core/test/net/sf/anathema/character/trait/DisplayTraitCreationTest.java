package net.sf.anathema.character.trait;

import net.sf.anathema.character.experience.IExperienceModel;
import net.sf.anathema.character.trait.experience.DummyExperienceModel;


public class DisplayTraitCreationTest extends AbstractDisplayTraitTest {

  @Override
  protected IExperienceModel createCharacterBasics() {
    return new DummyExperienceModel();
  }
}