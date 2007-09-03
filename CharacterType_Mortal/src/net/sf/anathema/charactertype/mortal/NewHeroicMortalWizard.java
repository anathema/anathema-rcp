package net.sf.anathema.charactertype.mortal;

import net.sf.anathema.basics.repository.creation.EmptyNewWizard;
import net.sf.anathema.character.core.create.NewCharacterActionDelegate;

public class NewHeroicMortalWizard extends EmptyNewWizard {

  @Override
  public boolean performFinish() {
    NewCharacterActionDelegate.createNewCharacter("net.sf.anathema.charactertype.heroicmortal");
    return true;
  }
}