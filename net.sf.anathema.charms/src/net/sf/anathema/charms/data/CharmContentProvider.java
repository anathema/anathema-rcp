package net.sf.anathema.charms.data;

import net.sf.anathema.charms.tree.CharmTreeExtensionPoint;
import net.sf.anathema.charms.view.ICharmIdExtractor;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphContentProvider;

public class CharmContentProvider implements IGraphContentProvider, ICharmIdExtractor {

  public String getSource(Object rel) {
    return ((CharmPrerequisite) rel).getSource();
  }

  public Object[] getElements(Object input) {
    String treeId = (String) input;
    return new CharmTreeExtensionPoint().getTree(treeId);
  }

  public String getDestination(Object rel) {
    return ((CharmPrerequisite) rel).getDestination();
  }

  public void dispose() {
    // nothing to do
  }

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // nothing to do
  }

  public String getCharmId(Object nodeData) {
    return (String) nodeData;
  }
}