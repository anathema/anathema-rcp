package net.sf.anathema.character.core.model.initialize;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.core.model.mark.IModelMarker;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;

import org.eclipse.core.runtime.IStatus;

public class ModelMarkerExtensionPoint implements IModelMarkerCollection {

  private final IPluginExtension[] pluginExtensions;

  public ModelMarkerExtensionPoint() {
    this(new EclipseExtensionPoint(CharacterCorePlugin.ID, "modelmarkers").getExtensions());
  }
  
  public ModelMarkerExtensionPoint(IPluginExtension ...pluginExtensions) {
    this.pluginExtensions = pluginExtensions;
  }
  
  public Iterable<IModelMarker> getModelMarkers(String modelId) {
    List<IModelMarker> modelMarkers = new ArrayList<IModelMarker>();
    for (IPluginExtension extension : pluginExtensions) {
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