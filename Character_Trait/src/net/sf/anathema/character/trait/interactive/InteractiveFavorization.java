package net.sf.anathema.character.trait.interactive;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.display.DisplayFavorization;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.ChangeableModelDisposable;

public class InteractiveFavorization extends DisplayFavorization implements IInteractiveFavorization {

  private final AggregatedDisposable allDisposables = new AggregatedDisposable();
  private final IExperience experience;

  public InteractiveFavorization(
      IBasicTrait basicTrait,
      IExperience experience,
      IFavorizationHandler favorizationHandler) {
    super(favorizationHandler, basicTrait);
    this.experience = experience;
  }

  @Override
  public boolean isFavorable() {
    return !experience.isExperienced() && super.isFavorable();
  }

  public void addFavorableChangeListener(IChangeListener listener) {
    experience.addChangeListener(listener);
    allDisposables.addDisposable(new ChangeableModelDisposable(experience, listener));
  }

  @Override
  public void toggleFavored() {
    getFavorizationHandler().toogleFavored(getBasicTrait().getTraitType());
  }

  @Override
  public void addFavoredChangeListener(IChangeListener listener) {
    getBasicTrait().getFavoredModel().addChangeListener(listener);
    allDisposables.addDisposable(new ChangeableModelDisposable(getBasicTrait().getFavoredModel(), listener));
  }

  @Override
  public void dispose() {
    allDisposables.dispose();
  }
}