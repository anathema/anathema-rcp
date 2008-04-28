package net.sf.anathema.character.trait.validator;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;
import net.sf.anathema.character.trait.validator.where.AllWhere;
import net.sf.anathema.character.trait.validator.where.ConditionalFactory;
import net.sf.anathema.character.trait.validator.where.ExtensionWhere;
import net.sf.anathema.character.trait.validator.where.IWhere;

public class ValidatorFactory implements IValidatorFactory {

  private static final String EXTENSION_POINT = "validator"; //$NON-NLS-1$
  private static final String TAG_CONDITION = "condition"; //$NON-NLS-1$
  private static final String TAG_WHERE = "where"; //$NON-NLS-1$

  @Override
  public List<IValidator> create(String templateId, IModelContainer container, String modelId, IBasicTrait trait) {
    IExperience experience = (IExperience) container.getModel(IExperience.MODEL_ID);
    List<IValidator> validators = new ArrayList<IValidator>();
    validators.add(new RespectCreationValueMinimum(experience, trait));
    validators.add(new RespectFavoredMinimum(trait));
    validators.add(new RespectValueMaximum(experience));
    for (IPluginExtension extension : new EclipseExtensionPoint(CharacterTraitPlugin.PLUGIN_ID, EXTENSION_POINT).getExtensions()) {
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
    AllWhere whereClause = createWhereClause(validatorElement.getElement(TAG_CONDITION));
    IValidatorFactory factory = new ConditionalFactory(validatorElement, whereClause);
    validators.addAll(factory.create(templateId, container, modelId, trait));
  }

  private AllWhere createWhereClause(IExtensionElement parent) {
    List<IWhere> allWheres = new ArrayList<IWhere>();
    for (IExtensionElement whereElement : parent.getElements(TAG_WHERE)) {
      allWheres.add(new ExtensionWhere(whereElement));
    }
    return new AllWhere(allWheres);
  }
}