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

  private static final String MODELMARKERS_EXTENSION_POINT = "modelmarkers"; //$NON-NLS-1$
  private static final String ATTRIB_CLASS = "class"; //$NON-NLS-1$
  private static final String ATTRIB_MODELID = "modelId"; //$NON-NLS-1$
  private final IPluginExtension[] pluginExtensions;

  public ModelMarkerExtensionPoint() {
    this(new EclipseExtensionPoint(CharacterCorePlugin.ID, MODELMARKERS_EXTENSION_POINT).getExtensions());
  }

  public ModelMarkerExtensionPoint(IPluginExtension... pluginExtensions) {
    this.pluginExtensions = pluginExtensions;
  }

  public Iterable<IModelMarker> getModelMarkers(String modelId) {
    List<IModelMarker> modelMarkers = new ArrayList<IModelMarker>();
    for (IPluginExtension extension : pluginExtensions) {
      for (IExtensionElement element : extension.getElements()) {
        if (modelId.equals(element.getAttribute(ATTRIB_MODELID))) {
          try {
            modelMarkers.add(element.getAttributeAsObject(ATTRIB_CLASS, IModelMarker.class));
          }
          catch (ExtensionException e) {
            String message = Messages.ModelMarkerExtensionPoint_ErrorReadingMarkers;
            CharacterCorePlugin.getDefaultInstance().log(IStatus.ERROR, message, e);
          }
        }
      }
    }
    return modelMarkers;
  }
}