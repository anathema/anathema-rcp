package net.sf.anathema.character.trait.display;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.template.ITraitTemplate;
import net.sf.anathema.lib.util.IIdentificate;

public class DisplayTrait implements IDisplayTrait {

  private final IDisplayFavorization favorization;
  private final IBasicTrait basicTrait;
  private final IExperience experience;
  private final ITraitTemplate traitTemplate;

  public DisplayTrait(
      IDisplayFavorization favorization,
      IBasicTrait basicTrait,
      IModelContainer container,
      ITraitTemplate traitTemplate) {
    this.basicTrait = basicTrait;
    experience = (IExperience) container.getModel(IExperience.MODEL_ID);
    this.traitTemplate = traitTemplate;
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
    return getMaximumValue(experience, traitTemplate);
  }

  public static int getMaximumValue(IExperience experience, ITraitTemplate traitTemplate) {
    int currentEssenceValue = getCurrentEssenceValue(experience);
    return traitTemplate.getMaximalValue(currentEssenceValue);
  }

  public static int getCurrentEssenceValue(IExperience experience) {
    return experience.isExperienced() ? 7 : 5;
  }

  @Override
  public IIdentificate getTraitType() {
    return basicTrait.getTraitType();
  }
}