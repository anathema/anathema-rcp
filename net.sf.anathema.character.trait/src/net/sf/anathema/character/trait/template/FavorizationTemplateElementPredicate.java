package net.sf.anathema.character.trait.template;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

public final class FavorizationTemplateElementPredicate implements IPredicate<IExtensionElement> {
  private static final String ATTRIB_CHARACTER_TEMPLATE_ID = "characterTemplateId"; //$NON-NLS-1$
  private static final String ATTRIB_MODEL_ID = "modelId"; //$NON-NLS-1$
  private final String templateId;
  private final String modelId;

  public static FavorizationTemplateElementPredicate ForTemplateAndModelId(String templateId, String modelId) {
    return new FavorizationTemplateElementPredicate(templateId, modelId);
  }

  private FavorizationTemplateElementPredicate(String templateId, String modelId) {
    this.templateId = templateId;
    this.modelId = modelId;
  }

  @Override
  public boolean evaluate(IExtensionElement element) {
    String elementTemplateId = element.getAttribute(ATTRIB_CHARACTER_TEMPLATE_ID);
    String elementModelId = element.getAttribute(ATTRIB_MODEL_ID);
    return templateId.equals(elementTemplateId) && modelId.equals(elementModelId);
  }
}