package net.sf.anathema.character.trait.validator.extension;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.character.trait.validator.IValidatorFactory;
import net.sf.anathema.character.trait.validator.where.IWhere;

public class ConditionalFactory implements IValidatorFactory {

  private static final String TAG_ALTERNATE_MINIMUM = "alternateMinimum"; //$NON-NLS-1$
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
    List<IValidator> unconditionedValidators = new ArrayList<IValidator>();
    addMinimumValidators(unconditionedValidators, trait);
    addAlternateMinimumValidators(unconditionedValidators, (ITraitCollectionModel) container.getModel(modelId), trait);
    List<IValidator> conditionedValidators = new ArrayList<IValidator>();
    for (IValidator validator : unconditionedValidators) {
      conditionedValidators.add(new ConditionalValidator(validator, whereClause, templateId, container, modelId, trait));
    }
    return conditionedValidators;
  }

  private void addAlternateMinimumValidators(
      List<IValidator> list,
      ITraitCollectionModel collectionModel,
      IBasicTrait trait) {
    for (IExtensionElement alternateMinimumElement : validatorElement.getElements(TAG_ALTERNATE_MINIMUM)) {
      AlternateMinimumBuilder builder = new AlternateMinimumBuilder();
      for (IExtensionElement minimumElement1 : alternateMinimumElement.getElements()) {
        String traitId = minimumElement1.getAttribute(ATTRIB_TRAIT_ID);
        int value = minimumElement1.getIntegerAttribute(ATTRIB_VALUE);
        if (!collectionModel.contains(traitId)) {
          builder.reset();
          break;
        }
        builder.addAlternative(collectionModel.getTrait(traitId), value);
      }
      IValidator validator = builder.createValidator(trait);
      if (validator != null) {
        list.add(validator);
      }
    }
  }

  private void addMinimumValidators(List<IValidator> list, IBasicTrait trait) {
    for (IExtensionElement minimumElement : validatorElement.getElements(TAG_MINIMUM)) {
      String traitId = minimumElement.getAttribute(ATTRIB_TRAIT_ID);
      if (traitId == null || trait.getTraitType().getId().equals(traitId)) {
        list.add(new ValidateMinimalValue(minimumElement.getIntegerAttribute(ATTRIB_VALUE)));
      }
    }
  }
}