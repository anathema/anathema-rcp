package net.sf.anathema.character.trait;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.ChangeableModelDisposable;

public class DisplayFavorization extends AggregatedDisposable implements IDisplayFavorization {

  private final IFavorizationHandler favorizationHandler;
  private final IBasicTrait basicTrait;
  private final IExperience experience;

  public DisplayFavorization(IBasicTrait basicTrait, IExperience experience, IFavorizationHandler favorizationHandler) {
    this.basicTrait = basicTrait;
    this.experience = experience;
    this.favorizationHandler = favorizationHandler;
  }

  @Override
  public boolean isFavorable() {
    return !experience.isExperienced() && favorizationHandler.isFavorable();
  }

  public void addFavorableChangeListener(IChangeListener listener) {
    experience.addChangeListener(listener);
    addDisposable(new ChangeableModelDisposable(experience, listener));
  }

  @Override
  public void toggleFavored() {
    favorizationHandler.toogleFavored(basicTrait.getTraitType());
  }

  @Override
  public boolean isFavored() {
    return basicTrait.getFavoredModel().getValue();
  }

  @Override
  public void addFavoredChangeListener(IChangeListener listener) {
    basicTrait.getFavoredModel().addChangeListener(listener);
    addDisposable(new ChangeableModelDisposable(basicTrait.getFavoredModel(), listener));
  }
}