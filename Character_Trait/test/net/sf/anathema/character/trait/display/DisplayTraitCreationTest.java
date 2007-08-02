package net.sf.anathema.character.trait.display;

import net.sf.anathema.character.experience.model.IExperience;
import net.sf.anathema.character.trait.experience.DummyExperience;


public class DisplayTraitCreationTest extends AbstractDisplayTraitTest {

  @Override
  protected IExperience createExperience() {
    return new DummyExperience();
  }
}