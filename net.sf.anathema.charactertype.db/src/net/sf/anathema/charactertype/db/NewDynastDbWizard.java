package net.sf.anathema.charactertype.db;

import net.sf.anathema.basics.repository.creation.EmptyNewWizard;
import net.sf.anathema.character.core.create.CharacterFactory;

public class NewDynastDbWizard extends EmptyNewWizard {

  @Override
  public boolean performFinish() {
    new CharacterFactory().createNewCharacter("net.sf.anathema.character.template.dynastdb", "Dragon-Blooded"); //$NON-NLS-1$ //$NON-NLS-2$
    return true;
  }
}