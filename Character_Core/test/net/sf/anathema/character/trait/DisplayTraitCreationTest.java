package net.sf.anathema.character.trait;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.experience.DummyExperienceModel;


public class DisplayTraitCreationTest extends AbstractDisplayTraitTest {

  @Override
  protected IExperience createCharacterBasics() {
    return new DummyExperienceModel();
  }
}