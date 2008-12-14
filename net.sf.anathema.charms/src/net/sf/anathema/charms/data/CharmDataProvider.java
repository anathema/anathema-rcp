package net.sf.anathema.charms.data;

public class CharmDataProvider implements ICharmDataProvider {

  @Override
  public String getDisplayName(String charmId) {
    return charmId;
  }
}