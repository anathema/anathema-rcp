package net.sf.anathema.charms.data;

import net.sf.anathema.charms.tree.CharmTreeExtensionPoint;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphContentProvider;

public class CharmContentProvider implements IGraphContentProvider {

  public Object getSource(Object rel) {
    return ((CharmPrerequisite) rel).getSource();
  }

  public Object[] getElements(Object input) {
    return new CharmTreeExtensionPoint().getTree("Solar: Thrown");
  }

  public Object getDestination(Object rel) {
    return ((CharmPrerequisite) rel).getDestination();
  }

  public void dispose() {
    // nothing to do
  }

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // nothing to do
  }
}