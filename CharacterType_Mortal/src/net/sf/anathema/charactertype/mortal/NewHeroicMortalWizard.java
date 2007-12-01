package net.sf.anathema.charactertype.mortal;

import net.sf.anathema.basics.repository.creation.EmptyNewWizard;
import net.sf.anathema.character.core.create.CharacterFactory;

public class NewHeroicMortalWizard extends EmptyNewWizard {

  @Override
  public boolean performFinish() {
    new CharacterFactory().createNewCharacter("net.sf.anathema.character.template.heroicmortal", "Mortal"); //$NON-NLS-1$ //$NON-NLS-2$
    return true;
  }
}