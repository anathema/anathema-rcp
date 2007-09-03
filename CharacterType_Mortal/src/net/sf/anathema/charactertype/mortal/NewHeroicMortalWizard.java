package net.sf.anathema.charactertype.mortal;

import net.sf.anathema.basics.repository.creation.EmptyNewWizard;
import net.sf.anathema.character.core.create.CharacterFactory;

public class NewHeroicMortalWizard extends EmptyNewWizard {

  @Override
  public boolean performFinish() {
    new CharacterFactory().createNewCharacter("net.sf.anathema.charactertype.heroicmortal"); //$NON-NLS-1$
    return true;
  }
}