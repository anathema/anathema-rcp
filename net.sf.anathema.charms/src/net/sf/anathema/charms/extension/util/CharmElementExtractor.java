package net.sf.anathema.charms.extension.util;

import net.sf.anathema.basics.eclipse.extension.AttributePredicate;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionPoint;
import net.sf.anathema.charms.tree.ICharmId;

public class CharmElementExtractor {

  private static final String ATTRIB_CHARM_ID = "charmId"; //$NON-NLS-1$
  private final IExtensionPoint extensionPoint;

  public CharmElementExtractor(IExtensionPoint extensionPoint) {
    this.extensionPoint = extensionPoint;
  }

  public IExtensionElement getExtensionElement(ICharmId charmId) {
    String idString = charmId.getIdPattern();
    AttributePredicate charmIdPredicate = AttributePredicate.FromNameAndValue(ATTRIB_CHARM_ID, idString);
    return extensionPoint.getFirst(charmIdPredicate);
  }
}