package net.sf.anathema.character.trait.display;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.lib.util.IIdentificate;

public class DisplayTrait implements IDisplayTrait {

  private final IDisplayFavorization favorization;
  private final IBasicTrait basicTrait;
  private final IExperience experience;
  private final int maxValue;

  public DisplayTrait(
      IDisplayFavorization favorization,
      IBasicTrait basicTrait,
      IModelContainer container,
      int maxValue) {
    this.basicTrait = basicTrait;
    this.maxValue = maxValue;
    experience = (IExperience) container.getModel(IExperience.MODEL_ID);
    this.favorization = favorization;
  }

  @Override
  public IDisplayFavorization getFavorization() {
    return favorization;
  }

  @Override
  public int getValue() {
    int experiencedValue = basicTrait.getExperiencedModel().getValue();
    int creationValue = basicTrait.getCreationModel().getValue();
    boolean isExperienced = experience.isExperienced();
    if (isExperienced && experiencedValue >= creationValue) {
      return experiencedValue;
    }
    return creationValue;
  }

  @Override
  public int getMaximalValue() {
    return maxValue;
  }

  public static int getCurrentEssenceValue(IExperience experience) {
    return experience.isExperienced() ? 7 : 5;
  }

  @Override
  public IIdentificate getTraitType() {
    return basicTrait.getTraitType();
  }
}