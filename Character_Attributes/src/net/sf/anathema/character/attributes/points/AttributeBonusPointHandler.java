package net.sf.anathema.character.attributes.points;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.repository.ModelResourceHandler;
import net.sf.anathema.character.points.configuration.IPointHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

import org.eclipse.core.resources.IResource;

public class AttributeBonusPointHandler extends AbstractExecutableExtension implements IPointHandler {

  private final IModelCollection modelCollection;
  private final IModelResourceHandler resourceHandler;

  public AttributeBonusPointHandler() {
    this(ModelCache.getInstance(), new ModelResourceHandler());
  }

  public AttributeBonusPointHandler(IModelCollection modelCollection, IModelResourceHandler resourceHandler) {
    this.modelCollection = modelCollection;
    this.resourceHandler = resourceHandler;
  }

  @Override
  public int getPoints(ICharacterId characterId) {
    ModelIdentifier identifier = new ModelIdentifier(characterId, Attributes.MODEL_ID);
    if (modelCollection.contains(identifier)) {
      return calculatePoints(identifier);
    }
    IResource resource = resourceHandler.getResource(identifier);
    if (resource.exists()) {
      return calculatePoints(identifier);
    }
    return 0;
  }

  private int calculatePoints(ModelIdentifier identifier) {
    ITraitCollectionModel attributes = (ITraitCollectionModel) modelCollection.getModel(identifier);
    return new AttributeBonusPointCalculator(attributes).calculate();
  }
}