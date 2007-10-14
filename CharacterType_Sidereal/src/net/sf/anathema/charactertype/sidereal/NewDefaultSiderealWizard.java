package net.sf.anathema.charactertype.sidereal;

import net.sf.anathema.basics.repository.creation.EmptyNewWizard;
import net.sf.anathema.character.core.create.CharacterFactory;

public class NewDefaultSiderealWizard extends EmptyNewWizard {

  @Override
  public boolean performFinish() {
    new CharacterFactory().createNewCharacter("net.sf.anathema.charactertype.defaultsidereal", "Sidereal"); //$NON-NLS-1$ //$NON-NLS-2$
    return true;
  }
}