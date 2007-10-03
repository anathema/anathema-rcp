package net.sf.anathema.character.core.model.initialize;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.basics.eclipse.resource.IMarkerHandle;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.mark.IModelMarker;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;

import org.eclipse.core.runtime.IStatus;

public class ModelInitializer implements IModelInitializer {

  private final IModel modelObject;
  private final IContentHandle contentHandle;
  private final IModelIdentifier modelIdentifier;

  public ModelInitializer(IModel modelObject, IContentHandle handler, IModelIdentifier modelIdentifier) {
    this.modelObject = modelObject;
    this.contentHandle = handler;
    this.modelIdentifier = modelIdentifier;
  }

  @Override
  public IModel getModel() {
    return modelObject;
  }

  @Override
  public void createMarkers() {
    IMarkerHandle markerHandler = (IMarkerHandle) contentHandle.getAdapter(IMarkerHandle.class);
    if (markerHandler == null) {
      return;
    }
    for (IModelMarker marking : getModelMarkers(modelIdentifier.getModelId())) {
      marking.mark(markerHandler, modelIdentifier);
    }
  }

  private Iterable<IModelMarker> getModelMarkers(String modelId) {
    List<IModelMarker> modelMarkers = new ArrayList<IModelMarker>();
    for (IPluginExtension extension : new EclipseExtensionPoint(CharacterCorePlugin.ID, "modelmarkers").getExtensions()) {
      for (IExtensionElement element : extension.getElements()) {
        if (modelId.equals(element.getAttribute("modelId"))) {
          try {
            modelMarkers.add(element.getAttributeAsObject("class", IModelMarker.class));
          }
          catch (ExtensionException e) {
            String message = "Error reading model marker.";
            CharacterCorePlugin.getDefaultInstance().log(IStatus.ERROR, message, e);
          }
        }
      }
    }
    return modelMarkers;
  }
}