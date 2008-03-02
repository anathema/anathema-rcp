package net.sf.anathema.character.freebies.attributes;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.attributes.model.IAttributesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.status.FavoredStatus;

public class FavoredAttributeCountHandler extends AbstractExecutableExtension implements IFreebiesHandler {

  private final IModelCollection modelProvider;

  public FavoredAttributeCountHandler() {
    this(ModelCache.getInstance());
  }
  
  public FavoredAttributeCountHandler(IModelCollection modelProvider) {
    this.modelProvider = modelProvider;
  }

  @Override
  public String getCreditId() {
    return "net.sf.anathema.character.attributes.count.favored"; //$NON-NLS-1$
  }

  @Override
  public int getPoints(ICharacterId id, int credit) {
    IModelIdentifier modelIdentifer = new ModelIdentifier(id, IAttributesPluginConstants.MODEL_ID);
    ITraitCollectionModel traitCollection = (ITraitCollectionModel) modelProvider.getModel(modelIdentifer);
    int count = 0;
    for (IBasicTrait trait : traitCollection.getTraits()) {
      if(trait.getStatusManager().getStatus() instanceof FavoredStatus) {
        count++;
      }
    }
    return count;
  }
}