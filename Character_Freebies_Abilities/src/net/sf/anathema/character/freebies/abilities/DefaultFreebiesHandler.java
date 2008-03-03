package net.sf.anathema.character.freebies.abilities;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.freebies.abilities.util.IAbilityFreebiesConstants;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;

public class DefaultFreebiesHandler extends AbstractExecutableExtension implements IFreebiesHandler {

  @Override
  public String getCreditId() {
    return IAbilityFreebiesConstants.UNLIMITED_CREDIT;
  }

  @Override
  public int getPoints(ICharacterId id, int credit) {
    // TODO: Case 189: Einstiegstelle
    return 0;
  }
}