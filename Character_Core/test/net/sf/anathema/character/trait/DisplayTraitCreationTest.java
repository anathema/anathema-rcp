package net.sf.anathema.character.trait;

import net.sf.anathema.character.experience.DummyCharacterBasics;
import net.sf.anathema.character.experience.ICharacterBasics;


public class DisplayTraitCreationTest extends AbstractDisplayTraitTest {

  @Override
  protected ICharacterBasics createCharacterBasics() {
    return new DummyCharacterBasics();
  }
}