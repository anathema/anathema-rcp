package net.sf.anathema.charms.tree;

import net.sf.anathema.charms.data.CharmPrerequisite;

public interface ICharmTreeProvider {
  
  public String[] getTreeList();

  public CharmPrerequisite[] getTree(String id);
}