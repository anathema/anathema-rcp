package net.sf.anathema.character.freebies.configuration.internal;

import net.sf.anathema.character.core.model.ICharacterId;

public class MissingFreebiesConfiguration implements IFreebiesConfiguration {

  private final String entryName;

  public MissingFreebiesConfiguration(String entryName) {
    this.entryName = entryName;
  }

  @Override
  public String getCredit(ICharacterId id) {
    return null;
  }

  @Override
  public String getName() {
    return entryName;
  }

  @Override
  public String getPoints(ICharacterId id) {
    return null;
  }
}