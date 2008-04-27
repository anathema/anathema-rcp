package net.sf.anathema.character.trait.validator.where;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.model.content.ModelContentChecker;
import net.sf.anathema.character.trait.IBasicTrait;

public class Where implements IWhere {

  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String ATTRIB_DEFINITION = "definition"; //$NON-NLS-1$
  private static final String TAG_MODEL_ID = "modelId"; //$NON-NLS-1$
  private static final String TAG_MODEL_CONTENT = "modelContent"; //$NON-NLS-1$
  private static final String TAG_TRAIT_ID = "traitId"; //$NON-NLS-1$
  private final IExtensionElement element;

  public Where(IExtensionElement element) {
    this.element = element;
  }

  @Override
  public boolean evaluate(String templateId, IModelContainer container, String modelId, IBasicTrait trait) {
    List<IWhere> allWheres = new ArrayList<IWhere>();
    IExtensionElement modelElement = element.getElement(TAG_MODEL_ID);
    if (modelElement != null) {
      allWheres.add(new ModelIdWhere(modelElement.getAttribute(ATTRIB_ID)));
    }
    IExtensionElement traitIdElement = element.getElement(TAG_TRAIT_ID);
    if (traitIdElement != null) {
      allWheres.add(new TraitIdWhere(traitIdElement.getAttribute(ATTRIB_ID)));
    }
    IExtensionElement modelContentElement = element.getElement(TAG_MODEL_CONTENT);
    if (modelContentElement != null) {
      String definition = modelContentElement.getAttribute(ATTRIB_DEFINITION);
      allWheres.add(new ModelContentWhere(definition, new ModelContentChecker()));
    }
    return new AllWhere(allWheres).evaluate(templateId, container, modelId, trait);
  }
}