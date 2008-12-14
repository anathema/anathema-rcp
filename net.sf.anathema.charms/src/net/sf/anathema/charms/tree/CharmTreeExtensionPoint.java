package net.sf.anathema.charms.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.charms.IPluginConstants;
import net.sf.anathema.charms.data.CharmPrerequisite;

public class CharmTreeExtensionPoint implements ITreeProvider, ITreeLookup, ITreeDataMap {

  private static final String TAG_TREEPART = "treepart"; //$NON-NLS-1$
  private static final String ATTRIB_CHARM_ID = "charmId"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String EXTENSION_NAME = "charmtree"; //$NON-NLS-1$
  private static final String ATTRIB_TREE_REFERENCE = "treeReference"; //$NON-NLS-1$
  private final IExtensionPoint extensionProvider;

  public CharmTreeExtensionPoint() {
    this(new EclipseExtensionPoint(IPluginConstants.PLUGIN_ID, EXTENSION_NAME));
  }

  public CharmTreeExtensionPoint(IExtensionPoint extensionProvider) {
    this.extensionProvider = extensionProvider;
  }

  @Override
  public List<String> getTreeList() {
    Set<String> set = new HashSet<String>();
    for (IPluginExtension extension : extensionProvider.getExtensions()) {
      for (IExtensionElement element : extension.getElements()) {
        if (element.getName().equals(TAG_TREEPART)) {
          set.add(element.getAttribute(ATTRIB_TREE_REFERENCE));
        }
      }
    }
    return new ArrayList<String>(set);
  }

  @Override
  public CharmPrerequisite[] getTree(String id) {
    Set<CharmPrerequisite> prerequisites = new HashSet<CharmPrerequisite>();
    List<String> explicitCharms = new ArrayList<String>();
    List<String> implicitCharms = new ArrayList<String>();
    for (IPluginExtension extension : extensionProvider.getExtensions()) {
      for (IExtensionElement treeElement : extension.getElements()) {
        if (treeElement.getAttribute(ATTRIB_TREE_REFERENCE).equals(id)) {
          for (IExtensionElement charmElement : treeElement.getElements()) {
            addCharm(charmElement, prerequisites, explicitCharms, implicitCharms);
          }
        }
      }
    }
    implicitCharms.removeAll(explicitCharms);
    for (String implicit : implicitCharms) {
      prerequisites.add(new CharmPrerequisite(null, implicit));
    }
    return prerequisites.toArray(new CharmPrerequisite[prerequisites.size()]);
  }

  private void addCharm(
      IExtensionElement charmElement,
      Collection<CharmPrerequisite> prerequisites,
      Collection<String> explicitCharms,
      Collection<String> implicitCharms) {
    String currentCharmId = charmElement.getAttribute(ATTRIB_ID);
    for (IExtensionElement prerequisiteElement : charmElement.getElements()) {
      String prerequisiteId = prerequisiteElement.getAttribute(ATTRIB_CHARM_ID);
      prerequisites.add(new CharmPrerequisite(prerequisiteId, currentCharmId));
      explicitCharms.add(currentCharmId);
      implicitCharms.add(prerequisiteId);
    }
    if (!explicitCharms.contains(currentCharmId)) {
      prerequisites.add(new CharmPrerequisite(null, currentCharmId));
      explicitCharms.add(currentCharmId);
    }
  }

  public String getTreeId(String charmId) {
    for (String treeId : getTreeList()) {
      CharmPrerequisite[] tree = getTree(treeId);
      for (CharmPrerequisite prerequisite : tree) {
        if (charmId.equals(prerequisite.getDestination()) || charmId.equals(prerequisite.getSource())) {
          return treeId;
        }
      }
    }
    return null;
  }

  @Override
  public TreeDto getData(final String id) {
    IExtensionElement treeElement = extensionProvider.getFirst(new TreeWithId(id));
    TreeDto dto = new TreeDto();
    dto.id = id;
    dto.name = treeElement == null ? id : treeElement.getAttribute("name");
    return dto;
  }
}