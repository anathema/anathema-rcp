package net.sf.anathema.character.trait.validator.where;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;

public class Where implements IWhere {

  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String TAG_MODEL_ID = "modelId"; //$NON-NLS-1$
  private final IExtensionElement element;

  public Where(IExtensionElement element) {
    this.element = element;
  }

  @Override
  public boolean evaluate(String templateId, IModelContainer container, String modelId, IBasicTrait trait) {
    IExtensionElement modelElement = element.getElement(TAG_MODEL_ID);
    String configuredId = modelElement.getAttribute(ATTRIB_ID);
    return modelElement == null || configuredId.equals(modelId);
  }
}