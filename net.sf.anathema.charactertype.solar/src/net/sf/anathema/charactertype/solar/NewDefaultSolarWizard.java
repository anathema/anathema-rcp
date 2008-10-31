package net.sf.anathema.charactertype.solar;

import net.sf.anathema.basics.repository.creation.EmptyNewWizard;
import net.sf.anathema.character.core.create.CharacterFactory;

public class NewDefaultSolarWizard extends EmptyNewWizard {

  @Override
  public boolean performFinish() {
    new CharacterFactory().createNewCharacter("net.sf.anathema.character.template.defaultsolar", "Solar"); //$NON-NLS-1$ //$NON-NLS-2$
    return true;
  }
}