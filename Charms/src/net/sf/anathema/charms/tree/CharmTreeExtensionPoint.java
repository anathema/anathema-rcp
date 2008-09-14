package net.sf.anathema.charms.tree;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.charms.CharmPrerequisite;
import net.sf.anathema.charms.IPluginConstants;

public class CharmTreeExtensionPoint implements ICharmTreeProvider {

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
          IExtensionElement charmElement = treeElement.getElements()[0];
          return new CharmPrerequisite[] { new CharmPrerequisite(null, charmElement.getAttribute(ATTRIB_ID)) };
        }
      }
    }
    return new CharmPrerequisite[0];
  }
}