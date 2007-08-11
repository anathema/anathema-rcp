package net.sf.anathema.character.freebies.configuration.internal;

import net.sf.anathema.character.core.model.ICharacterId;

public interface IFreebiesConfiguration {

  public String getName();

  public String getPoints(ICharacterId id);

  public String getCredit(ICharacterId id);
}