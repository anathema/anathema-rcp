package net.sf.anathema.character.trait;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.experience.DummyExperience;


public class DisplayTraitCreationTest extends AbstractDisplayTraitTest {

  @Override
  protected IExperience createExperience() {
    return new DummyExperience();
  }
}