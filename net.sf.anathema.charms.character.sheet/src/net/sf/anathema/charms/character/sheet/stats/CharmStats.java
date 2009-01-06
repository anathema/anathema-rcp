package net.sf.anathema.charms.character.sheet.stats;

import net.sf.anathema.charms.data.lookup.CharmNamesExtensionPoint;
import net.sf.anathema.charms.display.DisplayCharm;
import net.sf.anathema.charms.tree.ICharmId;

public class CharmStats extends AbstractCharmStats {

  private final ICharmId charmId;

  public CharmStats(ICharmId charmId, DisplayCharm charm) {
    super(charm);
    this.charmId = charmId;
  }

  public String getName() {
    return new CharmNamesExtensionPoint().getNameFor(charmId);
  }

  public String getGroupName() {
    //TODO Case 349: Generics sollen nur einmal auftauchen - das geht nicht, wenn sie ihr Primary Trait rausrücken
    return charmId.getPrimaryTrait();
  }
}