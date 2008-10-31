package net.sf.anathema.character.core.model.initialize;

import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.core.model.mark.IModelMarker;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;

public class ModelMarkerExtensionPoint implements IModelMarkerCollection {

  private static final String MODELMARKERS_EXTENSION_POINT = "modelmarkers"; //$NON-NLS-1$
  private final IPluginExtension[] pluginExtensions;

  public ModelMarkerExtensionPoint() {
    this(new EclipseExtensionPoint(CharacterCorePlugin.ID, MODELMARKERS_EXTENSION_POINT).getExtensions());
  }

  public ModelMarkerExtensionPoint(IPluginExtension... pluginExtensions) {
    this.pluginExtensions = pluginExtensions;
  }

  public Iterable<IModelMarker> getModelMarkers(final String modelId) {
    return new ClassConveyerBelt<IModelMarker>(
        CharacterCorePlugin.ID,
        IModelMarker.class,
        new ModelIdPredicate(modelId),
        pluginExtensions).getAllObjects();
  }
}