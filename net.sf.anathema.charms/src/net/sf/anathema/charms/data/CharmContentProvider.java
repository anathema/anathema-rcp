package net.sf.anathema.charms.data;

import net.sf.anathema.charms.tree.CharmTreeProvider;
import net.sf.anathema.charms.tree.ICharmId;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphContentProvider;

public class CharmContentProvider implements IGraphContentProvider {

  public ICharmId getSource(Object rel) {
    return ((CharmPrerequisite) rel).getSource();
  }

  public Object[] getElements(Object input) {
    if (input == null) {
      return null;
    }
    String treeId = (String) input;
    return CharmTreeProvider.Create().getTree(treeId);
  }

  public ICharmId getDestination(Object rel) {
    return ((CharmPrerequisite) rel).getDestination();
  }

  public void dispose() {
    // nothing to do
  }

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // nothing to do
  }
}