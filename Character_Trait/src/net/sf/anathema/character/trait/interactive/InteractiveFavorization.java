package net.sf.anathema.character.trait.interactive;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.ChangeableModelDisposable;

public class InteractiveFavorization extends AggregatedDisposable implements IInteractiveFavorization {

  private final IFavorizationHandler favorizationHandler;
  private final IBasicTrait basicTrait;
  private final IExperience experience;

  public InteractiveFavorization(IBasicTrait basicTrait, IExperience experience, IFavorizationHandler favorizationHandler) {
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