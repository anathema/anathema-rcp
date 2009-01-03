package net.sf.anathema.charms.tree;

import org.eclipse.core.runtime.IExecutableExtension;

public interface ITreeDataMap extends IExecutableExtension {

  public TreeDto getData(String id);
}