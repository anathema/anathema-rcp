package net.sf.anathema.character.trait.validator;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.model.MinimalValueFactory;
import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;
import net.sf.anathema.character.trait.validator.where.AllWhere;
import net.sf.anathema.character.trait.validator.where.ConditionalFactory;
import net.sf.anathema.character.trait.validator.where.IWhere;
import net.sf.anathema.character.trait.validator.where.Where;

public class ValidatorFactory implements IValidatorFactory {

  @Override
  public List<IValidator> create(String templateId, IModelContainer container, String modelId, IBasicTrait trait) {
    IExperience experience = (IExperience) container.getModel(IExperience.MODEL_ID);
    List<IValidator> validators = new ArrayList<IValidator>();
    validators.add(new RespectCreationValueMinimum(experience, trait));
    validators.add(new RespectFavoredMinimum(trait));
    MinimalValueFactory minValueFactory = new MinimalValueFactory(0, templateId);
    validators.add(new RespectTemplateMinimum(trait.getTraitType().getId(), minValueFactory));
    validators.add(new RespectValueMaximum(experience));
    for (IPluginExtension extension : new EclipseExtensionPoint(CharacterTraitPlugin.PLUGIN_ID, "validator").getExtensions()) {
      for (IExtensionElement extensionElement : extension.getElements()) {
        addConfiguredValidators(validators, extensionElement, templateId, container, modelId, trait);
      }
    }
    return validators;
  }

  private void addConfiguredValidators(
      List<IValidator> validators,
      IExtensionElement validatorElement,
      String templateId,
      IModelContainer container,
      String modelId,
      IBasicTrait trait) {
    AllWhere whereClause = createWhereClause(validatorElement.getElement("condition"));
    IValidatorFactory factory = new ConditionalFactory(validatorElement, whereClause);
    validators.addAll(factory.create(templateId, container, modelId, trait));
  }

  private AllWhere createWhereClause(IExtensionElement parent) {
    List<IWhere> allWheres = new ArrayList<IWhere>();
    for (IExtensionElement whereElement : parent.getElements("where")) {
      allWheres.add(new Where(whereElement));
    }
    return new AllWhere(allWheres);
  }
}