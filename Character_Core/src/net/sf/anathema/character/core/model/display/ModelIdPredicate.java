package net.sf.anathema.character.core.model.display;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

public final class ModelIdPredicate implements IPredicate<IExtensionElement> {
  private final String modelId;

  public ModelIdPredicate(String modelId) {
    this.modelId = modelId;
  }

  @Override
  public boolean evaluate(IExtensionElement element) {
    return element.getAttribute("modelId").equals(modelId);
  }
}