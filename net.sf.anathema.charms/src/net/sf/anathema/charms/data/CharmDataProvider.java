package net.sf.anathema.charms.data;

import net.sf.anathema.charms.data.lookup.CharmNamesExtensionPoint;

public class CharmDataProvider implements ICharmDataProvider {

  private final INameMap names;

  public CharmDataProvider() {
    this(new CharmNamesExtensionPoint());
  }

  public CharmDataProvider(INameMap names) {
    this.names = names;
  }

  @Override
  public String getDisplayName(String charmId) {
    return names.getNameFor(charmId);
  }
}