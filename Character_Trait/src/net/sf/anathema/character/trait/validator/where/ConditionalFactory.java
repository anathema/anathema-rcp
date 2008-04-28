package net.sf.anathema.character.trait.validator.where;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.character.trait.validator.IValidatorFactory;

public class ConditionalFactory implements IValidatorFactory {

  private static final String TAG_MINIMUM = "minimum"; //$NON-NLS-1$
  private static final String ATTRIB_VALUE = "value"; //$NON-NLS-1$
  private static final String ATTRIB_TRAIT_ID = "traitId"; //$NON-NLS-1$
  private final IExtensionElement validatorElement;
  private final IWhere whereClause;

  public ConditionalFactory(IExtensionElement validatorElement, IWhere whereClause) {
    this.validatorElement = validatorElement;
    this.whereClause = whereClause;
  }

  @Override
  public List<IValidator> create(String templateId, IModelContainer container, String modelId, IBasicTrait trait) {
    List<IValidator> validators = new ArrayList<IValidator>();
    for (IExtensionElement minimumElement : validatorElement.getElements(TAG_MINIMUM)) {
      String traitId = minimumElement.getAttribute(ATTRIB_TRAIT_ID);
      if (traitId == null || trait.getTraitType().getId().equals(traitId)) {
        IValidator validator = new ValidateMinimalValue(minimumElement.getIntegerAttribute(ATTRIB_VALUE));
        validators.add(new ConditionalValidator(validator, whereClause, templateId, container, modelId, trait));
      }
    }
    return validators;
  }
}