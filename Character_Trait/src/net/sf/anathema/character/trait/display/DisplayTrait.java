package net.sf.anathema.character.trait.display;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.rules.ITraitTemplate;
import net.sf.anathema.character.trait.rules.internal.RuleTrait;
import net.sf.anathema.lib.util.IIdentificate;

public class DisplayTrait implements IDisplayTrait {

  private final DisplayFavorization favorization;
  private final RuleTrait ruleTrait;
  private final IBasicTrait basicTrait;

  public DisplayTrait(
      IFavorizationHandler favorizationHandler,
      IBasicTrait basicTrait,
      IExperience experience,
      ITraitTemplate traitTemplate) {
    this.basicTrait = basicTrait;
    this.favorization = new DisplayFavorization(favorizationHandler, basicTrait);
    this.ruleTrait = new RuleTrait(basicTrait, experience, traitTemplate);
  }

  @Override
  public IDisplayFavorization getFavorization() {
    return favorization;
  }

  @Override
  public int getValue() {
    return ruleTrait.getValue();
  }

  @Override
  public int getMaximalValue() {
    return ruleTrait.getMaximalValue();
  }

  @Override
  public IIdentificate getTraitType() {
    return basicTrait.getTraitType();
  }
}