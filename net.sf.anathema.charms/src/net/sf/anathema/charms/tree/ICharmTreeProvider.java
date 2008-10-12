package net.sf.anathema.charms.tree;

import java.util.List;

import net.sf.anathema.charms.data.CharmPrerequisite;

public interface ICharmTreeProvider {

  public List<String> getTreeList();

  public CharmPrerequisite[] getTree(String id);
}