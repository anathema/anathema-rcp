package net.sf.anathema.character.freebies.view;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.freebies.configuration.internal.IFreebiesConfiguration;

public interface IFreebiesConfigurationsProvider {

  public IFreebiesConfiguration[] get(ICharacterId characterId);
}