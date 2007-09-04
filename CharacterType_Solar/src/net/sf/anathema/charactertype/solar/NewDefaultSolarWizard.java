package net.sf.anathema.charactertype.solar;

import net.sf.anathema.basics.repository.creation.EmptyNewWizard;
import net.sf.anathema.character.core.create.CharacterFactory;

public class NewDefaultSolarWizard extends EmptyNewWizard {

  @Override
  public boolean performFinish() {
    new CharacterFactory().createNewCharacter("net.sf.anathema.charactertype.defaultsolar"); //$NON-NLS-1$
    return true;
  }
}