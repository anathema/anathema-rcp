package net.sf.anathema.character.trait.validator.extension;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.core.model.content.ModelContentChecker;
import net.sf.anathema.character.trait.validator.where.AllWhere;
import net.sf.anathema.character.trait.validator.where.IWhere;
import net.sf.anathema.character.trait.validator.where.ValidationDto;
import net.sf.anathema.character.trait.validator.where.WhereCharacterType;
import net.sf.anathema.character.trait.validator.where.WhereModelContent;
import net.sf.anathema.character.trait.validator.where.WhereModelId;
import net.sf.anathema.character.trait.validator.where.WhereTemplateId;
import net.sf.anathema.character.trait.validator.where.WhereTraitId;

public class ExtensionWhere implements IWhere {

  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String ATTRIB_DEFINITION = "definition"; //$NON-NLS-1$
  private static final String TAG_MODEL_ID = "modelId"; //$NON-NLS-1$
  private static final String TAG_MODEL_CONTENT = "modelContent"; //$NON-NLS-1$
  private static final String TAG_TRAIT_ID = "traitId"; //$NON-NLS-1$
  private static final String TAG_TEMPLATE_ID = "templateId"; //$NON-NLS-1$
  private static final String TAG_CHARACTER_TYPE = "characterType"; //$NON-NLS-1$
  private final IExtensionElement element;

  public ExtensionWhere(IExtensionElement element) {
    this.element = element;
  }

  @Override
  public boolean evaluate(ValidationDto dto) {
    List<IWhere> allWheres = new ArrayList<IWhere>();
    IExtensionElement modelElement = element.getElement(TAG_MODEL_ID);
    if (modelElement != IExtensionElement.NO_ELEMENT) {
      allWheres.add(new WhereModelId(modelElement.getAttribute(ATTRIB_ID)));
    }
    IExtensionElement traitIdElement = element.getElement(TAG_TRAIT_ID);
    if (traitIdElement != null) {
      allWheres.add(new WhereTraitId(traitIdElement.getAttribute(ATTRIB_ID)));
    }
    IExtensionElement modelContentElement = element.getElement(TAG_MODEL_CONTENT);
    if (modelContentElement != null) {
      String definition = modelContentElement.getAttribute(ATTRIB_DEFINITION);
      allWheres.add(new WhereModelContent(definition, new ModelContentChecker()));
    }
    IExtensionElement templateIdElement = element.getElement(TAG_TEMPLATE_ID);
    if (templateIdElement != null) {
      allWheres.add(new WhereTemplateId(templateIdElement.getAttribute(ATTRIB_ID)));
    }
    IExtensionElement characterTypeElement = element.getElement(TAG_CHARACTER_TYPE);
    if (characterTypeElement != null) {
      allWheres.add(new WhereCharacterType(characterTypeElement.getAttribute(ATTRIB_ID)));
    }
    return new AllWhere(allWheres).evaluate(dto);
  }
}