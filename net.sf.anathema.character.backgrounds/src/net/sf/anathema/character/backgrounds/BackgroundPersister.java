package net.sf.anathema.character.backgrounds;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.persistence.AbstractTraitCollectionPersister;

public class BackgroundPersister extends AbstractTraitCollectionPersister<BackgroundTemplate, IBackgroundModel> {

  @Override
  protected IBackgroundModel createModelFor(IBasicTrait[] traits) {
    return new BackgroundModel(traits);
  }

  @Override
  public IBackgroundModel createNew(BackgroundTemplate template) {
    return new BackgroundModel();
  }
}