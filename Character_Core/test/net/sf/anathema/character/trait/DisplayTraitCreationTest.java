package net.sf.anathema.character.trait;

import net.sf.anathema.character.basics.ICharacterBasics;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;

public class DisplayTraitCreationTest extends AbstractIntValueModelTest {

  @Before
  public void createTrait() {
    ICharacterBasics basics = new ICharacterBasics() {
      @Override
      public boolean isExperienced() {
        return false;
      }
    };
    this.model = new DisplayTrait(new BasicTrait(new Identificate("test")), basics); //$NON-NLS-1$
  }

}
