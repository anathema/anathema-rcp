package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.basics.eclipse.resource.IMarkerHandle;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.initialize.IModelMarkerCollection;
import net.sf.anathema.character.core.model.initialize.ModelMarkerExtensionPoint;
import net.sf.anathema.character.core.model.mark.IModelMarker;

public class ModelInitializer implements IModelInitializer {

  private final IModel modelObject;
  private final IMarkerHandle markerHandle;
  private final IModelIdentifier modelIdentifier;
  private final IModelMarkerCollection modelMarkerCollection;

  public ModelInitializer(IModel modelObject, IContentHandle file, IModelIdentifier modelIdentifier) {
    this(modelObject, file, modelIdentifier, new ModelMarkerExtensionPoint());
  }

  public ModelInitializer(
      IModel modelObject,
      IContentHandle file,
      IModelIdentifier modelIdentifier,
      IModelMarkerCollection modelMarkerCollection) {
    this.modelObject = modelObject;
    this.markerHandle = (IMarkerHandle) file.getAdapter(IMarkerHandle.class);
    this.modelIdentifier = modelIdentifier;
    this.modelMarkerCollection = modelMarkerCollection;
  }

  @Override
  public IModel getModel() {
    return modelObject;
  }

  @Override
  public void initialize() {
    getModel().setClean();
    createMarkers();
  }

  private void createMarkers() {
    if (markerHandle == null) {
      return;
    }
    for (IModelMarker marking : modelMarkerCollection.getModelMarkers(modelIdentifier.getModelId())) {
      marking.mark(markerHandle, modelIdentifier);
    }
  }
  
  protected final IModelIdentifier getModelIdentifier() {
    return modelIdentifier;
  }
}