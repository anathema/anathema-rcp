/**
 * 
 */
package net.sf.anathema.character.core.model.initialize;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

public final class ModelIdPredicate implements IPredicate<IExtensionElement> {
  private final String modelId;
  private static final String ATTRIB_MODELID = "modelId"; //$NON-NLS-1$

  public ModelIdPredicate(String modelId) {
    this.modelId = modelId;
  }

  @Override
  public boolean evaluate(IExtensionElement element) {
    return modelId.equals(element.getAttribute(ATTRIB_MODELID));
  }
}