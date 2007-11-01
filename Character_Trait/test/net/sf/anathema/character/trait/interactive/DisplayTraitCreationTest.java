package net.sf.anathema.character.trait.interactive;

import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.experience.IExperience;


public class DisplayTraitCreationTest extends AbstractDisplayTraitTest {

  @Override
  protected IExperience createExperience() {
    return new DummyExperience();
  }
}