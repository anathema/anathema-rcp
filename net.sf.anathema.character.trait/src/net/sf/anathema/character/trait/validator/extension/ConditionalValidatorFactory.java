package net.sf.anathema.character.trait.validator.extension;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.character.trait.validator.IValidatorFactory;
import net.sf.anathema.character.trait.validator.where.IWhere;
import net.sf.anathema.character.trait.validator.where.ValidationDto;

public class ConditionalValidatorFactory implements IValidatorFactory {

  private static final String TAG_ALTERNATE_MINIMUM = "alternateMinimum"; //$NON-NLS-1$
  private static final String TAG_MINIMUM = "minimum"; //$NON-NLS-1$
  private static final String TAG_MAXIMUM = "maximum"; //$NON-NLS-1$
  private static final String ATTRIB_VALUE = "value"; //$NON-NLS-1$
  private static final String ATTRIB_TRAIT_ID = "traitId"; //$NON-NLS-1$
  private final IExtensionElement validatorElement;
  private final IWhere whereClause;

  public ConditionalValidatorFactory(IExtensionElement validatorElement, IWhere whereClause) {
    this.validatorElement = validatorElement;
    this.whereClause = whereClause;
  }

  @Override
  public List<IValidator> create(ValidationDto validationObject) {
    List<IValidator> unconditionedValidators = new ArrayList<IValidator>();
    IBasicTrait trait = validationObject.trait;
    addMinimumValidators(unconditionedValidators, trait);
    addMaximumValidators(unconditionedValidators, trait);
    ITraitCollectionModel traitCollection = (ITraitCollectionModel) validationObject.container.getModel(validationObject.modelId);
    addAlternateMinimumValidators(unconditionedValidators, traitCollection, trait);
    List<IValidator> conditionedValidators = new ArrayList<IValidator>();
    for (IValidator validator : unconditionedValidators) {
      conditionedValidators.add(new ConditionalValidator(validator, whereClause, validationObject));
    }
    return conditionedValidators;
  }

  private void addAlternateMinimumValidators(
      List<IValidator> list,
      ITraitCollectionModel collectionModel,
      IBasicTrait trait) {
    for (IExtensionElement alternateMinimumElement : validatorElement.getElements(TAG_ALTERNATE_MINIMUM)) {
      AlternateMinimumBuilder builder = new AlternateMinimumBuilder();
      for (IExtensionElement minimumElement : alternateMinimumElement.getElements()) {
        String traitId = minimumElement.getAttribute(ATTRIB_TRAIT_ID);
        int value = minimumElement.getIntegerAttribute(ATTRIB_VALUE);
        if (!collectionModel.contains(traitId)) {
          builder.reset();
          break;
        }
        builder.addAlternative(collectionModel.getTrait(traitId), value);
      }
      list.add(builder.createValidator(trait));
    }
  }

  private void addMinimumValidators(List<IValidator> list, IBasicTrait trait) {
    IExtensionElement[] minimumElements = validatorElement.getElements(TAG_MINIMUM);
    for (IExtensionElement element : getElementsForTrait(minimumElements, trait)) {
      list.add(new ValidateMinimalValue(element.getIntegerAttribute(ATTRIB_VALUE)));
    }
  }

  private void addMaximumValidators(List<IValidator> list, IBasicTrait trait) {
    IExtensionElement[] maximumElements = validatorElement.getElements(TAG_MAXIMUM);
    for (IExtensionElement element : getElementsForTrait(maximumElements, trait)) {
      list.add(new ValidateMaximalValue(element.getIntegerAttribute(ATTRIB_VALUE)));
    }
  }

  private List<IExtensionElement> getElementsForTrait(IExtensionElement[] elements, IBasicTrait trait) {
    List<IExtensionElement> traitElements = new ArrayList<IExtensionElement>();
    for (IExtensionElement element : elements) {
      String traitId = element.getAttribute(ATTRIB_TRAIT_ID);
      if (traitId == null || trait.getTraitType().getId().equals(traitId)) {
        traitElements.add(element);
      }
    }
    return traitElements;
  }
}