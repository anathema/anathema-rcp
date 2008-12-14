package net.sf.anathema.charms.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.disy.commons.core.util.IClosure;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.charms.IPluginConstants;
import net.sf.anathema.charms.data.CharmPrerequisite;

public class CharmTreeExtensionPoint implements ITreeProvider, ITreeLookup, ITreeDataMap {

  private static final String TAG_GENERIC_CHARMS = "genericCharms"; //$NON-NLS-1$

  public static final class AddGenericCharmClosure implements IClosure<IExtensionElement> {
    private final CharmBuilder charmBuilder;

    public AddGenericCharmClosure(CharmBuilder charmBuilder) {
      this.charmBuilder = charmBuilder;
    }

    @Override
    public void execute(IExtensionElement element) throws RuntimeException {
      if (element.getName().equals(TAG_GENERIC_CHARMS)) {
        for (IExtensionElement charmElement : element.getElements()) {
          charmBuilder.addCharm(charmElement);
        }
      }
    }
  }

  public static final class AddCharmClosure implements IClosure<IExtensionElement> {
    private final String id;
    private final CharmBuilder charmBuilder;

    public AddCharmClosure(String id, CharmBuilder charmBuilder) {
      this.id = id;
      this.charmBuilder = charmBuilder;
    }

    @Override
    public void execute(IExtensionElement element) throws RuntimeException {
      if (element.getName().equals(TAG_TREEPART) && element.getAttribute(ATTRIB_TREE_REFERENCE).equals(id)) {
        for (IExtensionElement charmElement : element.getElements()) {
          charmBuilder.addCharm(charmElement);
        }
      }
    }
  }

  private static final String ATTRIB_NAME = "name"; //$NON-NLS-1$
  private static final String TAG_TREEPART = "treepart"; //$NON-NLS-1$
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
  public CharmPrerequisite[] getTree(final String id) {
    final CharmBuilder charmBuilder = new CharmBuilder(id);
    extensionProvider.forAllDo(new AddGenericCharmClosure(charmBuilder));
    extensionProvider.forAllDo(new AddCharmClosure(id, charmBuilder));
    return charmBuilder.create();
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
    dto.name = treeElement == null ? id : treeElement.getAttribute(ATTRIB_NAME);
    return dto;
  }
}