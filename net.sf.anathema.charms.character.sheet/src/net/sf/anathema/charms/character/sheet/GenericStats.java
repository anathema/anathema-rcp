package net.sf.anathema.charms.character.sheet;

import net.sf.anathema.charms.character.sheet.stats.CharmStats;
import net.sf.anathema.charms.display.DisplayCharm;
import net.sf.anathema.charms.tree.ICharmId;

public class GenericStats extends CharmStats {

  public GenericStats(ICharmId charmId, DisplayCharm charm) {
    super(charmId, charm);
  }

  @Override
  public String getGroupName() {
    return "Generic Charms";
  }
}