package net.sf.anathema.character.spiritualtraits.validator;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.spiritualtraits.virtues.VirtueSumCalculator;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.validator.ISpecialValidator;
import net.sf.anathema.character.trait.validator.where.ValidationDto;

public class VirtueSumMinimumValidator extends UnconfiguredExecutableExtension implements ISpecialValidator {

  private VirtueSumCalculator virtueSumCalculator;

  public void initValidation(ValidationDto validationObject) {
    IModelContainer container = validationObject.container;
    ITraitCollectionModel spiritualModel = (ITraitCollectionModel) container.getModel(IPluginConstants.MODEL_ID);
    virtueSumCalculator = new VirtueSumCalculator(spiritualModel);
  }

  @Override
  public int getValidValue(int value) {
    int virtueSum =virtueSumCalculator.getSumOfTwoHighestVirtues();
    return Math.max(value, virtueSum);
  }
}