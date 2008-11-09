package net.sf.anathema.charms.character.tree;

import net.sf.anathema.basics.eclipse.extension.AttributePredicate;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionProvider;
import net.sf.anathema.charms.character.IPluginConstants;
import net.sf.anathema.charms.tree.CharmTreeExtensionPoint;
import net.sf.anathema.charms.tree.ICharmTreeLookup;

public class CharmTraitLookup implements ITraitIdLookup {

  private static final String EXTENSION_POINT = "treetraits"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String ATTRIB_PRIMARY_TRAIT = "primaryTrait"; //$NON-NLS-1$
  private final ICharmTreeLookup treeLookup;
  private final IExtensionProvider extensionPoint;

  public CharmTraitLookup() {
    this(new CharmTreeExtensionPoint(), new EclipseExtensionPoint(IPluginConstants.PLUGIN_ID, EXTENSION_POINT));
  }

  public CharmTraitLookup(ICharmTreeLookup treeLookup, IExtensionProvider extensionPoint) {
    this.treeLookup = treeLookup;
    this.extensionPoint = extensionPoint;
  }

  public String getTraitId(String charmId) {
    final String treeId = treeLookup.getTreeId(charmId);
    IExtensionElement treeElement = extensionPoint.getFirst(new AttributePredicate(ATTRIB_ID, treeId));
    return treeElement == null ? null : treeElement.getAttribute(ATTRIB_PRIMARY_TRAIT);
  }
}