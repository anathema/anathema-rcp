package net.sf.anathema.character.spiritualtraits.validator;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.validator.ISpecialValidator;
import net.sf.anathema.character.trait.validator.where.ValidationDto;

public class RespectEssenceMaximum extends UnconfiguredExecutableExtension implements ISpecialValidator {

  private static final int MINIMAL_MAXIMUM = 5;
  private IExperience experience;
  private ITraitCollectionModel spiritualTraits;

  @Override
  public void initValidation(ValidationDto validationObject) {
    this.experience = (IExperience) validationObject.container.getModel(IExperience.MODEL_ID);
    this.spiritualTraits = (ITraitCollectionModel) validationObject.container.getModel(IPluginConstants.MODEL_ID);
  }

  @Override
  public int getValidValue(int value) {
    return Math.min(value, getCurrentEssenceValue());
  }

  private int getCurrentEssenceValue() {
    if (!experience.isExperienced()) {
      return MINIMAL_MAXIMUM;
    }
    IBasicTrait essence = spiritualTraits.getTrait(IPluginConstants.ESSENCE_ID);
    int essenceValue = essence.getExperiencedModel().getValue();
    return Math.max(MINIMAL_MAXIMUM, essenceValue);
  }
}