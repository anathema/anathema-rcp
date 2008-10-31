package net.sf.anathema.character.freebies.configuration.internal;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;

public class FreebiesConfiguration implements IFreebiesConfiguration {

  private final IFreebiesHandler handler;
  private final String entryName;
  private final ICreditManager manager;

  public FreebiesConfiguration(String entryName, IFreebiesHandler handler, ICreditManager manager) {
    this.entryName = entryName;
    this.handler = handler;
    this.manager = manager;
  }

  @Override
  public String getName() {
    return entryName;
  }

  @Override
  public IFreebiesResult getFreebies(ICharacterId characterId) {
    int credit = manager.getCredit(characterId, handler.getCreditId());
    int value = handler.getPoints(characterId, credit);
    return new FreebiesResult(credit, value);
  }
}