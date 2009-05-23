package net.sf.anathema.charms.character.model;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.charms.character.plugin.CharmCharacterPlugin;

public class VirtualCharmEvaluation {

  static final String ATTRIB_CHARM_ID_PATTERN = "charmIdPattern"; //$NON-NLS-1$
  static final String TAG_VIRTUAL_CHARM = "virtualCharm"; //$NON-NLS-1$
  private final EclipseExtensionPoint extensionPoint;

  public VirtualCharmEvaluation() {
    this.extensionPoint = new EclipseExtensionPoint(CharmCharacterPlugin.PLUGIN_ID, "specialcharms"); //$NON-NLS-1$
  }

  public boolean isVirtual(final String pattern) {
    IsVirtualCharmWithIdPattern predicate = new IsVirtualCharmWithIdPattern(pattern);
    IExtensionElement virtualElement = extensionPoint.getFirst(predicate);
    return virtualElement != null;
  }
}