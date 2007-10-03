package net.sf.anathema.character.core.model.initialize;

import net.sf.anathema.basics.eclipse.resource.IMarkerHandle;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.mark.IModelMarker;

public class ModelInitializer implements IModelInitializer {

  private final IModel modelObject;
  private final IMarkerHandle markerHandle;
  private final IModelIdentifier modelIdentifier;
  private final IModelMarkerCollection modelMarkerCollection;

  public ModelInitializer(IModel modelObject, IMarkerHandle handler, IModelIdentifier modelIdentifier) {
    this(modelObject, handler, modelIdentifier, new ModelMarkerExtensionPoint());
  }

  public ModelInitializer(
      IModel modelObject,
      IMarkerHandle handler,
      IModelIdentifier modelIdentifier,
      IModelMarkerCollection modelMarkerCollection) {
    this.modelObject = modelObject;
    this.markerHandle = handler;
    this.modelIdentifier = modelIdentifier;
    this.modelMarkerCollection = modelMarkerCollection;
  }

  @Override
  public IModel getModel() {
    return modelObject;
  }

  @Override
  public void createMarkers() {
    if (markerHandle == null) {
      return;
    }
    for (IModelMarker marking : modelMarkerCollection.getModelMarkers(modelIdentifier.getModelId())) {
      marking.mark(markerHandle, modelIdentifier);
    }
  }
}