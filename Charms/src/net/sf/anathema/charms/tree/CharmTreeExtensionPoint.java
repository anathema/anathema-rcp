package net.sf.anathema.charms.tree;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.charms.CharmPrerequisite;
import net.sf.anathema.charms.IPluginConstants;

public class CharmTreeExtensionPoint implements ICharmTreeProvider {

  private static final String ATTRIB_CHARM_ID = "charmId"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String EXTENSION_NAME = "charmtree"; //$NON-NLS-1$
  private static final String ATTRIB_TREE_REFERENCE = "treeReference"; //$NON-NLS-1$
  private final IExtensionProvider extensionProvider;

  public CharmTreeExtensionPoint() {
    this(new EclipseExtensionPoint(IPluginConstants.PLUGIN_ID, EXTENSION_NAME));
  }

  public CharmTreeExtensionPoint(IExtensionProvider extensionProvider) {
    this.extensionProvider = extensionProvider;
  }

  @Override
  public CharmPrerequisite[] getTree(String id) {
    for (IPluginExtension extension : extensionProvider.getExtensions()) {
      for (IExtensionElement treeElement : extension.getElements()) {
        if (treeElement.getAttribute(ATTRIB_TREE_REFERENCE).equals(id)) {
          List<CharmPrerequisite> prerequisites = new ArrayList<CharmPrerequisite>();
          List<String> explicitCharms = new ArrayList<String>();
          List<String> implicitCharms = new ArrayList<String>();
          for (IExtensionElement charmElement : treeElement.getElements()) {
            addCharm(charmElement, prerequisites, explicitCharms, implicitCharms);
          }
          implicitCharms.removeAll(explicitCharms);
          for (String implicit : implicitCharms) {
            prerequisites.add(new CharmPrerequisite(null, implicit));
          }
          return prerequisites.toArray(new CharmPrerequisite[prerequisites.size()]);
        }
      }
    }
    return new CharmPrerequisite[0];
  }

  private void addCharm(
      IExtensionElement charmElement,
      List<CharmPrerequisite> prerequisites,
      List<String> explicitCharms,
      List<String> implicitCharms) {
    String currentCharmId = charmElement.getAttribute(ATTRIB_ID);
    for (IExtensionElement prerequisiteElement : charmElement.getElements()) {
      String prerequisiteId = prerequisiteElement.getAttribute(ATTRIB_CHARM_ID);
      // do not add, when already exists
      prerequisites.add(new CharmPrerequisite(prerequisiteId, currentCharmId));
      explicitCharms.add(currentCharmId);
      implicitCharms.add(prerequisiteId);
    }
    if (!explicitCharms.contains(currentCharmId)) {
      prerequisites.add(new CharmPrerequisite(null, currentCharmId));
      explicitCharms.add(currentCharmId);
    }
  }
}