package net.sf.anathema.charms.tree.operations;

import net.disy.commons.core.predicate.ICheck;
import net.sf.anathema.charms.data.CharmPrerequisite;

public class ContainsCharm implements ICheck {

  private final CharmPrerequisite[] tree;
  private final String charmId;

  public ContainsCharm(CharmPrerequisite[] tree, String charmId) {
    this.tree = tree;
    this.charmId = charmId;
  }

  @Override
  public boolean isConfirmed() {
    for (CharmPrerequisite prerequisite : tree) {
      if (prerequisite.connects(charmId)) {
        return true;
      }
    }
    return false;
  }
}