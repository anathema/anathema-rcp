package net.sf.anathema.character.trait.validator;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.model.MinimalValueFactory;

public class ValidatorFactory implements IValidatorFactory {

  @Override
  public List<IValidator> create(String templateId, IModelContainer container, IBasicTrait trait) {
    IExperience experience = (IExperience) container.getModel(IExperience.MODEL_ID);
    List<IValidator> validators = new ArrayList<IValidator>();
    validators.add(new RespectCreationValueMinimum(experience, trait));
    validators.add(new RespectFavoredMinimum(trait));
    // TODO: Case 183: Minimalwerte für Attribute sind immer 1
    MinimalValueFactory minValueFactory = new MinimalValueFactory(0, templateId);
    validators.add(new RespectTemplateMinimum(trait.getTraitType().getId(), minValueFactory));
    validators.add(new RespectValueMaximum(experience));
    return validators;
  }

}
