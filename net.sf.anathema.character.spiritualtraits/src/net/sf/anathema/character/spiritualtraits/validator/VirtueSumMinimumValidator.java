package net.sf.anathema.character.spiritualtraits.validator;

import java.util.Arrays;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.spiritualtraits.virtues.Virtues;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.validator.ISpecialValidator;
import net.sf.anathema.character.trait.validator.where.ValidationDto;

public class VirtueSumMinimumValidator extends UnconfiguredExecutableExtension implements ISpecialValidator {

  private IBasicTrait[] virtues;

  public void initValidation(ValidationDto validationObject) {
    IModelContainer container = validationObject.container;
    ITraitCollectionModel spiritualModel = (ITraitCollectionModel) container.getModel(IPluginConstants.MODEL_ID);
    IBasicTrait[] virtueTraits = new Virtues(spiritualModel).getTraits();
    this.virtues = Arrays.copyOf(virtueTraits, virtueTraits.length);
  }

  @Override
  public int getValidValue(int value) {
    Arrays.sort(virtues, 0, virtues.length, new ReverseCreationValueComparator());
    int virtueSum = virtues[0].getCreationModel().getValue() + virtues[1].getCreationModel().getValue();
    return Math.max(value, virtueSum);
  }
}