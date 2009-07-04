package net.sf.anathema.charms.data;

import net.sf.anathema.charms.providing.CharmProvidingExtensionPoint;
import net.sf.anathema.charms.tree.ICharmId;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphContentProvider;

public class CharmContentProvider implements IGraphContentProvider {

  public ICharmId getSource(Object rel) {
    return ((CharmPrerequisite) rel).getSource();
  }

  public Object[] getElements(Object input) {
    if (input == null) {
      return new Object[0];
    }
    String treeId = (String) input;
    return CharmProvidingExtensionPoint.CreateTreeProvider().getTree(treeId);
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