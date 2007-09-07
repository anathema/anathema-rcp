package net.sf.anathema.character.attributes.points;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class FavoredAttributeCountHandler extends AbstractExecutableExtension implements IFreebiesHandler {

  private final IModelProvider modelProvider;

  public FavoredAttributeCountHandler() {
    this(ModelCache.getInstance());
  }
  
  public FavoredAttributeCountHandler(IModelProvider modelProvider) {
    this.modelProvider = modelProvider;
  }

  @Override
  public String getCreditId() {
    return "net.sf.anathema.character.attributes.count.favored"; //$NON-NLS-1$
  }

  @Override
  public int getPoints(ICharacterId id, int credit) {
    IModelIdentifier modelIdentifer = new ModelIdentifier(id, Attributes.MODEL_ID);
    ITraitCollectionModel traitCollection = (ITraitCollectionModel) modelProvider.getModel(modelIdentifer);
    int count = 0;
    for (IBasicTrait trait : traitCollection.getTraits()) {
      if(trait.getFavoredModel().getValue()) {
        count++;
      }
    }
    return count;
  }
}