package net.sf.anathema.charms.tree;

import java.util.List;

import net.sf.anathema.charms.data.CharmPrerequisite;

import org.eclipse.core.runtime.IExecutableExtension;

public interface ITreeProvider extends ITreeDataMap, IExecutableExtension {

  public List<String> getTreeList();

  public CharmPrerequisite[] getTree(String id);
}