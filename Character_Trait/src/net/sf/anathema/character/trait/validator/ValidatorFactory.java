package net.sf.anathema.character.trait.validator;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.template.ITraitTemplate;

public class ValidatorFactory implements IValidatorFactory {

  @Override
  public List<IValidator> create(IModelContainer container, IBasicTrait trait, ITraitTemplate template) {
    IExperience experience = (IExperience) container.getModel(IExperience.MODEL_ID);
    List<IValidator> validators = new ArrayList<IValidator>();
    validators.add(new RespectCreationValueMinimum(experience, trait));
    validators.add(new RespectFavoredMinimum(trait));
    validators.add(new RespectTemplateMinimum(template));
    validators.add(new RespectValueMaximum(experience));
    return validators;
  }

}
