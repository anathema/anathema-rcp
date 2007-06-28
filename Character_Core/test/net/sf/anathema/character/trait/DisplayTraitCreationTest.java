package net.sf.anathema.character.trait;

import net.sf.anathema.character.basics.DummyCharacterBasics;
import net.sf.anathema.character.basics.ICharacterBasics;


public class DisplayTraitCreationTest extends AbstractDisplayTraitTest {

  @Override
  protected ICharacterBasics createCharacterBasics() {
    return new DummyCharacterBasics();
  }
}