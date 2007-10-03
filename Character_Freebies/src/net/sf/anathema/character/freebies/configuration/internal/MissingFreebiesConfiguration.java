package net.sf.anathema.character.freebies.configuration.internal;

import net.sf.anathema.character.core.character.ICharacterId;

public class MissingFreebiesConfiguration implements IFreebiesConfiguration {

  private final String entryName;

  public MissingFreebiesConfiguration(String entryName) {
    this.entryName = entryName;
  }

  @Override
  public String getName() {
    return entryName;
  }

  @Override
  public IFreebiesResult getFreebies(ICharacterId characterId) {
    return new IFreebiesResult() {

      @Override
      public String getCredit() {
        return null;
      }

      @Override
      public String getPoints() {
        return null;
      }

      @Override
      public boolean isValid() {
        return false;
      }
    };
  }
}