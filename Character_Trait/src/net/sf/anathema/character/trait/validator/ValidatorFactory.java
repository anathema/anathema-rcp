package net.sf.anathema.character.trait.validator;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.model.MinimalValueFactory;

public class ValidatorFactory implements IValidatorFactory {

  private final int defaultMinimumValue;

  public ValidatorFactory(int defaultMinimumValue) {
    this.defaultMinimumValue = defaultMinimumValue;
  }

  @Override
  public List<IValidator> create(String templateId, IModelContainer container, IBasicTrait trait) {
    IExperience experience = (IExperience) container.getModel(IExperience.MODEL_ID);
    List<IValidator> validators = new ArrayList<IValidator>();
    validators.add(new RespectCreationValueMinimum(experience, trait));
    validators.add(new RespectFavoredMinimum(trait));
    MinimalValueFactory minValueFactory = new MinimalValueFactory(defaultMinimumValue, templateId);
    validators.add(new RespectTemplateMinimum(trait.getTraitType().getId(), minValueFactory));
    validators.add(new RespectValueMaximum(experience));
    return validators;
  }

}
