package net.sf.anathema.charms.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionPoint;
import net.sf.anathema.charms.IPluginConstants;
import net.sf.anathema.charms.data.CharmPrerequisite;
import net.sf.anathema.charms.tree.operations.AddCharms;
import net.sf.anathema.charms.tree.operations.CollectTreeIds;
import net.sf.anathema.charms.tree.operations.ContainsCharm;
import net.sf.anathema.charms.tree.operations.ForGenerics;
import net.sf.anathema.charms.tree.operations.ForTreePart;

public class CharmTreeExtensionPoint implements ITreeProvider, ITreeLookup, ITreeDataMap {

  private static final String EXTENSION_NAME = "charmtree"; //$NON-NLS-1$
  public static final String TAG_TREEPART = "treepart"; //$NON-NLS-1$
  public static final String ATTRIB_TREE_REFERENCE = "treeReference"; //$NON-NLS-1$
  private static final String ATTRIB_NAME = "name"; //$NON-NLS-1$
  private static final String ATTRIB_PRIMARY_TRAIT = "primaryTrait"; //$NON-NLS-1$
  private static final String ATTRIB_CHARACTER_TYPE = "characterType"; //$NON-NLS-1$
  private final IExtensionPoint extensionProvider;

  public CharmTreeExtensionPoint() {
    this(new EclipseExtensionPoint(IPluginConstants.PLUGIN_ID, EXTENSION_NAME));
  }

  public CharmTreeExtensionPoint(IExtensionPoint extensionProvider) {
    this.extensionProvider = extensionProvider;
  }

  @Override
  public List<String> getTreeList() {
    final Set<String> set = new HashSet<String>();
    extensionProvider.forAllDo(new CollectTreeIds(set));
    return new ArrayList<String>(set);
  }

  @Override
  public CharmPrerequisite[] getTree(final String id) {
    String primaryTrait = getData(id).primaryTrait;
    final CharmBuilder charmBuilder = new CharmBuilder(primaryTrait);
    extensionProvider.forAllDo(new AddCharms(new ForGenerics(), charmBuilder));
    extensionProvider.forAllDo(new AddCharms(new ForTreePart(id), charmBuilder));
    return charmBuilder.create();
  }

  public String getTreeId(String charmId) {
    for (String treeId : getTreeList()) {
      CharmPrerequisite[] tree = getTree(treeId);
      if (new ContainsCharm(tree, charmId).isConfirmed()) {
        return treeId;
      }
    }
    return null;
  }

  @Override
  public TreeDto getData(final String id) {
    IExtensionElement treeElement = extensionProvider.getFirst(new TreeWithId(id));
    TreeDto dto = new TreeDto();
    dto.id = id;
    if (treeElement == null) {
      dto.name = id;
      return dto;
    }
    dto.name = treeElement.getAttribute(ATTRIB_NAME);
    dto.primaryTrait = treeElement.getAttribute(ATTRIB_PRIMARY_TRAIT);
    dto.characterType = treeElement.getAttribute(ATTRIB_CHARACTER_TYPE);
    return dto;
  }
}