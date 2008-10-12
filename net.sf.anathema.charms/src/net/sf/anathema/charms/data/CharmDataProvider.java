package net.sf.anathema.charms.data;

public class CharmDataProvider implements ICharmDataProvider {
  private static final String SEPERATOR = ": "; //$NON-NLS-1$

  @Override
  public String getDisplayName(String charmId) {
    return charmId.split(SEPERATOR)[1];
  }
}