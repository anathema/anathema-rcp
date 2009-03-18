package net.sf.anathema.character.trait.validator.extension;

import static net.sf.anathema.character.trait.validator.extension.IValidatorXmlConstants.*;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;
import net.sf.anathema.character.trait.validator.ISpecialValidator;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.character.trait.validator.where.ValidationDto;

public class UnconditionalValidatorFactory {
  private static final String TAG_ALTERNATE_MINIMUM = "alternateMinimum"; //$NON-NLS-1$
  private static final String TAG_MINIMUM = "minimum"; //$NON-NLS-1$
  private static final String TAG_MAXIMUM = "maximum"; //$NON-NLS-1$
  private static final String TAG_SPECIAL = "special"; //$NON-NLS-1$
  private static final String ATTRIB_CLASS = "class"; //$NON-NLS-1$
  private static final String ATTRIB_VALUE = "value"; //$NON-NLS-1$
  private final List<IValidator> list = new ArrayList<IValidator>();
  private final IExtensionElement validatorElement;
  private final ValidationDto dto;

  public UnconditionalValidatorFactory(IExtensionElement validatorElement, ValidationDto dto) {
    this.validatorElement = validatorElement;
    this.dto = dto;
  }

  public List<IValidator> create() {
    addMinimumValidators(dto.trait);
    addMaximumValidators(dto.trait);
    addAlternateMinimumValidators();
    addSpecialValidators(dto.trait);
    return list;
  }

  private void addSpecialValidators(IBasicTrait trait) {
    IExtensionElement[] specialElements = validatorElement.getElements(TAG_SPECIAL);
    for (IExtensionElement element : getElementsForTrait(specialElements, trait)) {
      addSpecialValidator(element);
    }
  }

  private void addSpecialValidator(IExtensionElement element) {
    try {
      ISpecialValidator validator = element.getAttributeAsObject(ATTRIB_CLASS, ISpecialValidator.class);
      validator.initValidation(dto);
      list.add(validator);
    }
    catch (Exception e) {
      String message = "Error initializing trait validator";
      new Logger(CharacterTraitPlugin.PLUGIN_ID).error(message, e);
    }
  }

  private void addMinimumValidators(IBasicTrait trait) {
    IExtensionElement[] minimumElements = validatorElement.getElements(TAG_MINIMUM);
    for (IExtensionElement element : getElementsForTrait(minimumElements, trait)) {
      list.add(new ValidateMinimalValue(element.getIntegerAttribute(ATTRIB_VALUE)));
    }
  }

  private void addMaximumValidators(IBasicTrait trait) {
    IExtensionElement[] maximumElements = validatorElement.getElements(TAG_MAXIMUM);
    for (IExtensionElement element : getElementsForTrait(maximumElements, trait)) {
      list.add(new ValidateMaximalValue(element.getIntegerAttribute(ATTRIB_VALUE)));
    }
  }

  private IExtensionElement[] getElementsForTrait(IExtensionElement[] elements, final IBasicTrait trait) {
    return ArrayUtilities.filter(elements, new HasTraitId(trait));
  }

  private void addAlternateMinimumValidators() {
    for (IExtensionElement alternateMinimumElement : validatorElement.getElements(TAG_ALTERNATE_MINIMUM)) {
      AlternateMinimumBuilder builder = new AlternateMinimumBuilder();
      for (IExtensionElement minimumElement : alternateMinimumElement.getElements()) {
        String traitId = minimumElement.getAttribute(ATTRIB_TRAIT_ID);
        ITraitCollectionModel traitCollection = dto.getModel();
        if (!traitCollection.contains(traitId)) {
          builder.reset();
          break;
        }
        IBasicTrait trait = traitCollection.getTrait(traitId);
        int value = minimumElement.getIntegerAttribute(ATTRIB_VALUE);
        builder.addAlternative(trait, value);
      }
      list.add(builder.createValidator(dto.trait));
    }
  }
}