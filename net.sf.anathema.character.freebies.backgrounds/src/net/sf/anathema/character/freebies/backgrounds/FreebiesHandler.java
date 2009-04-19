package net.sf.anathema.character.freebies.backgrounds;

import net.sf.anathema.character.backgrounds.model.IBackgroundModel;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.configuration.AbstractLimitedFreebiesHandler;
import net.sf.anathema.character.freebies.configuration.TraitFreebieLimit;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class FreebiesHandler extends AbstractLimitedFreebiesHandler {

  private final IModelCollection models;

  public FreebiesHandler() {
    this(ModelCache.getInstance());
  }

  public FreebiesHandler(IModelCollection models) {
    super(new TraitFreebieLimit(), 0);
    this.models = models;
  }

  @Override
  public String getCreditId() {
    return BackgroundFreebiesConstants.CREDIT_ID;
  }

  @Override
  protected Iterable<IBasicTrait> getTraits(ICharacterId id) {
    ModelIdentifier identifier = new ModelIdentifier(id, IBackgroundModel.MODEL_ID);
    ITraitCollectionModel traits = (ITraitCollectionModel) models.getModel(identifier);
    return traits;
  }
}