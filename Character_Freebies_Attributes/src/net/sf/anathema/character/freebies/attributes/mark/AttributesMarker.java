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
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.freebies.configuration.CreditManager;

public class AttributesMarker extends AbstractExecutableExtension implements IModelMarker {

  private static final String TERTIARY_MARKER = "net.sf.anathema.markers.unspent.tertiary.attribute"; //$NON-NLS-1$
  private static final String SECONDARY_MARKER = "net.sf.anathema.markers.unspent.secondary.attribute"; //$NON-NLS-1$
  private static final String PRIMARY_MARKER = "net.sf.anathema.markers.unspent.primary.attribute"; //$NON-NLS-1$

  @Override
  public void mark(IMarkerHandle markerHandler, IModelIdentifier modelIdentifier) {
    ICharacterId characterId = modelIdentifier.getCharacterId();
    IModelCollection modelCache = ModelCache.getInstance();
    IAttributeCreditCollection creditCollection = new AttributeCreditCollection(new CreditManager(), characterId);
    IChangeableModel changeableModel = modelCache.getModel(modelIdentifier);
    TotalDotsSpent dotsSpent = new TotalDotsSpent(AttributesContext.create(characterId, modelCache));
    new AttributeResourceMarker(
        creditCollection,
        dotsSpent,
        changeableModel,
        markerHandler,
        new AttributeGroupMarkerId(PriorityGroup.Primary, PRIMARY_MARKER)).mark();
    new AttributeResourceMarker(
        creditCollection,
        dotsSpent,
        changeableModel,
        markerHandler,
        new AttributeGroupMarkerId(PriorityGroup.Secondary, SECONDARY_MARKER)).mark();
    new AttributeResourceMarker(
        creditCollection,
        dotsSpent,
        changeableModel,
        markerHandler,
        new AttributeGroupMarkerId(PriorityGroup.Tertiary, TERTIARY_MARKER)).mark();
  }
}