package net.sf.anathema.character.freebies.virtues.internal;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.configuration.AbstractLimitedFreebiesHandler;
import net.sf.anathema.character.freebies.configuration.ITraitFreebieLimit;
import net.sf.anathema.character.freebies.configuration.TraitFreebieLimit;
import net.sf.anathema.character.freebies.virtues.VirtueFreebiesConstants;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.spiritualtraits.virtues.Virtues;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class VirtueFreebiesHandler extends AbstractLimitedFreebiesHandler {

  private final IModelCollection models;

  public VirtueFreebiesHandler() {
    this(ModelCache.getInstance());
  }

  public VirtueFreebiesHandler(IModelCollection models) {
    this(models, new TraitFreebieLimit());
  }

  public VirtueFreebiesHandler(IModelCollection models, ITraitFreebieLimit limit) {
    super(limit, 1);
    this.models = models;
  }

  @Override
  public String getCreditId() {
    return VirtueFreebiesConstants.FREEBIE_CREDIT;
  }

  @Override
  protected Virtues getTraits(ICharacterId id) {
    ModelIdentifier identifier = new ModelIdentifier(id, IPluginConstants.MODEL_ID);
    ITraitCollectionModel traits = (ITraitCollectionModel) models.getModel(identifier);
    return new Virtues(traits);
  }
}