package net.sf.anathema.character.trait.validator;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;
import net.sf.anathema.character.trait.validator.extension.ConditionalValidatorFactory;
import net.sf.anathema.character.trait.validator.extension.ExtensionWhereFactory;
import net.sf.anathema.character.trait.validator.where.IWhere;
import net.sf.anathema.character.trait.validator.where.ValidationDto;

public class ValidatorFactory implements IValidatorFactory {

  private static final String EXTENSION_POINT = "validator"; //$NON-NLS-1$
  private static final String TAG_CONDITION = "condition"; //$NON-NLS-1$

  @Override
  public List<IValidator> create(ValidationDto dto) {
    IExperience experience = (IExperience) dto.container.getModel(IExperience.MODEL_ID);
    List<IValidator> validators = new ArrayList<IValidator>();
    validators.add(new RespectCreationValueMinimum(experience, dto.trait));
    validators.add(new RespectFavoredMinimum(dto.trait));
    validators.add(new SubTraitValidator(experience, dto.getModel(), dto.trait));
    for (IPluginExtension extension : new EclipseExtensionPoint(CharacterTraitPlugin.PLUGIN_ID, EXTENSION_POINT).getExtensions()) {
      for (IExtensionElement extensionElement : extension.getElements()) {
        addConfiguredValidators(validators, extensionElement, dto);
      }
    }
    return validators;
  }

  private void addConfiguredValidators(
      List<IValidator> validators,
      IExtensionElement validatorElement,
      ValidationDto validationObject) {
    IWhere whereClause = createWhereClause(validatorElement.getElement(TAG_CONDITION));
    IValidatorFactory factory = new ConditionalValidatorFactory(validatorElement, whereClause);
    validators.addAll(factory.create(validationObject));
  }

  private IWhere createWhereClause(IExtensionElement element) {
    return new ExtensionWhereFactory().createWhereClause(element);
  }
}