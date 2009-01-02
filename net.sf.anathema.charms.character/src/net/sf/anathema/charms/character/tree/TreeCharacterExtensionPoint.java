package net.sf.anathema.charms.character.tree;

import net.sf.anathema.basics.eclipse.extension.AttributePredicate;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionPoint;
import net.sf.anathema.charms.character.IPluginConstants;

public class TreeCharacterExtensionPoint implements ITreeCharacterDataMap {

  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String ATTRIB_PRIMARY_TRAIT = "primaryTrait"; //$NON-NLS-1$
  private static final String ATTRIB_CHARACTER_TYPE = "characterType"; //$NON-NLS-1$
  private static final String EXTENSION_POINT = "charmtrees"; //$NON-NLS-1$
  private final IExtensionPoint extensionPoint;

  public TreeCharacterExtensionPoint() {
    this(new EclipseExtensionPoint(IPluginConstants.PLUGIN_ID, EXTENSION_POINT));
  }

  public TreeCharacterExtensionPoint(IExtensionPoint extensionPoint) {
    this.extensionPoint = extensionPoint;
  }

  public String getTraitId(String treeId) {
    IExtensionElement treeElement = getElementFor(treeId);
    return treeElement == null ? null : treeElement.getAttribute(ATTRIB_PRIMARY_TRAIT);
  }

  @Override
  public String getCharacterType(String treeId) {
    IExtensionElement treeElement = getElementFor(treeId);
    return treeElement == null ? null : treeElement.getAttribute(ATTRIB_CHARACTER_TYPE);
  }

  private IExtensionElement getElementFor(String treeId) {
    AttributePredicate treeIdPredicate = AttributePredicate.FromNameAndValue(ATTRIB_ID, treeId);
    return extensionPoint.getFirst(treeIdPredicate);
  }
}