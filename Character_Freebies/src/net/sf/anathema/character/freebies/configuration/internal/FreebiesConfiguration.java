package net.sf.anathema.character.freebies.configuration.internal;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;

public class FreebiesConfiguration implements IFreebiesConfiguration {

  private final IFreebiesHandler handler;
  private final String entryName;
  private final ICreditManager manager;
  private final String creditId;

  public FreebiesConfiguration(String entryName, String creditId, IFreebiesHandler handler, ICreditManager manager) {
    this.entryName = entryName;
    this.creditId = creditId;
    this.handler = handler;
    this.manager = manager;
  }

  @Override
  public String getCredit(ICharacterId characterId) {
    return String.valueOf(manager.getCredit(characterId, creditId));
  }

  @Override
  public String getName() {
    return entryName;
  }

  @Override
  public String getPoints(ICharacterId characterId) {
    return String.valueOf(handler.getPoints(characterId));
  }
}