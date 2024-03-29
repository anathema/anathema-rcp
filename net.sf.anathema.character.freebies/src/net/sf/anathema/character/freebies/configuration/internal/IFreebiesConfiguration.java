package net.sf.anathema.character.freebies.configuration.internal;

import net.sf.anathema.character.core.character.ICharacterId;

public interface IFreebiesConfiguration {

  public String getName();

  public IFreebiesResult getFreebies(ICharacterId characterId);
}