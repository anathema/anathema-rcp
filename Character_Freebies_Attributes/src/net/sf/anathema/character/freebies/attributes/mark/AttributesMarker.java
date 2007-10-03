package net.sf.anathema.character.freebies.attributes.mark;

import net.disy.commons.core.model.IChangeableModel;
import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.eclipse.resource.IMarkerHandle;
import net.sf.anathema.character.attributes.model.AttributesContext;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.mark.IModelMarker;
import net.sf.anathema.character.freebies.attributes.calculation.AttributeCreditCollection;
import net.sf.anathema.character.freebies.attributes.calculation.IAttributeCreditCollection;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;

public class AttributesMarker extends AbstractExecutableExtension implements IModelMarker {

  @Override
  public void mark(IMarkerHandle markerHandler, IModelIdentifier modelIdentifier) {
    ICharacterId characterId = modelIdentifier.getCharacterId();
    IModelCollection modelCache = ModelCache.getInstance();
    IAttributeCreditCollection creditCollection = new AttributeCreditCollection(new CreditManager(), characterId);
    ITraitCollectionContext context = AttributesContext.create(characterId, modelCache);
    IChangeableModel changeableModel = modelCache.getModel(modelIdentifier);
    new AttributeResourceMarker(creditCollection, context, changeableModel, markerHandler).mark();
  }
}