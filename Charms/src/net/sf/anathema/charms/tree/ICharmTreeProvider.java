package net.sf.anathema.charms.tree;

import net.sf.anathema.charms.CharmPrerequisite;

public interface ICharmTreeProvider {

  public CharmPrerequisite[] getTree(String id);
}