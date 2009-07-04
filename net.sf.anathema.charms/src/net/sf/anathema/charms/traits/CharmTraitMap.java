package net.sf.anathema.charms.traits;

import java.util.List;

import net.sf.anathema.charms.tree.ICharmId;

public class CharmTraitMap implements ICharmTraitMap {

  private final List<IExecutableCharmTraitMap> allMaps;

  public CharmTraitMap(List<IExecutableCharmTraitMap> allMaps) {
    this.allMaps = allMaps;
  }

  @Override
  public CharmTraits getTraits(ICharmId charmId) {
    for (IExecutableCharmTraitMap map : allMaps) {
      CharmTraits traits = map.getTraits(charmId);
      if (traits != null) {
        return traits;
      }
    }
    return createDefaultTraits();
  }

  private CharmTraits createDefaultTraits() {
    CharmTraits traits = new CharmTraits();
    traits.essenceMinimum = 1;
    traits.primaryMinimum = 1;
    return traits;
  }
}