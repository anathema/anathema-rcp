package net.sf.anathema.charms.extension.traits;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.charms.IPluginConstants;
import net.sf.anathema.charms.extension.util.CharmElementExtractor;
import net.sf.anathema.charms.traits.CharmTraits;
import net.sf.anathema.charms.traits.IExecutableCharmTraitMap;
import net.sf.anathema.charms.tree.ICharmId;

public class CharmTraitExtensionPoint extends UnconfiguredExecutableExtension implements IExecutableCharmTraitMap {

  private static final String EXTENSION_POINT_ID = "charmtraits"; //$NON-NLS-1$
  private final CharmElementExtractor charmElementExtractor;

  public CharmTraitExtensionPoint() {
    this(new EclipseExtensionPoint(IPluginConstants.PLUGIN_ID, EXTENSION_POINT_ID));
  }

  public CharmTraitExtensionPoint(IExtensionPoint extensionPoint) {
    this.charmElementExtractor = new CharmElementExtractor(extensionPoint);
  }

  @Override
  public CharmTraits getTraits(ICharmId charmId) {
    IExtensionElement extensionElement = charmElementExtractor.getExtensionElement(charmId);
    if (extensionElement == IExtensionElement.NO_ELEMENT) {
      return null;
    }
    return extractCharmTraits(extensionElement);
  }

  private CharmTraits extractCharmTraits(IExtensionElement extensionElement) {
    CharmTraits charmTraits = new CharmTraits();
    charmTraits.essenceMinimum = getValueFromAttribute(extensionElement, "essenceMinimum");
    charmTraits.primaryMinimum = getValueFromAttribute(extensionElement, "primaryMinimum");
    return charmTraits;
  }

  private int getValueFromAttribute(IExtensionElement extensionElement, String attributeName) {
    if (extensionElement.hasAttribute(attributeName)) {
      return extensionElement.getIntegerAttribute(attributeName);
    }
    return 1;
  }
}