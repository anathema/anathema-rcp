package net.sf.anathema.charactertype.lunar;

import net.sf.anathema.basics.repository.creation.EmptyNewWizard;
import net.sf.anathema.character.core.create.CharacterFactory;

public class NewPactLunarWizard extends EmptyNewWizard {

  @Override
  public boolean performFinish() {
    new CharacterFactory().createNewCharacter("net.sf.anathema.character.template.pactlunar", "Lunar"); //$NON-NLS-1$ //$NON-NLS-2$
    return true;
  }
}