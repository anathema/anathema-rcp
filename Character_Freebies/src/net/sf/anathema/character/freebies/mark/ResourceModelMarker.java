package net.sf.anathema.character.freebies.mark;

import net.disy.commons.core.model.IChangeableModel;
import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.eclipse.resource.IMarkerHandle;
import net.sf.anathema.basics.repository.problems.MarkerProblem;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.core.repository.ModelDisplayNameProvider;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;
import net.sf.anathema.character.core.resource.CharacterModelEditorOpener;
import net.sf.anathema.character.freebies.plugin.ICharacterFreebiesPluginConstants;
import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class ResourceModelMarker implements IDisposable {
  private final IChangeListener markListener = new IChangeListener() {
    @Override
    public void stateChanged() {
      markFile();
    }
  };
  private final IMarkerHandle markerHandle;
  private final IChangeableModel changeableModel;
  private final IModelMarker modelMarker;

  public ResourceModelMarker(IChangeableModel changeableModel, IMarkerHandle markerHandle, IModelMarker modelMarker) {
    this.changeableModel = changeableModel;
    this.markerHandle = markerHandle;
    this.modelMarker = modelMarker;
  }

  public void mark() {
    changeableModel.addChangeListener(markListener);
    markFile();
  }

  private void markFile() {
    if (!markerHandle.exists()) {
      return;
    }
    boolean warning = modelMarker.isActive();
    try {
      if (warning) {
        IMarker[] markers = markerHandle.findMarkers(modelMarker.getMarkerId(), true, IResource.DEPTH_ZERO);
        if (markers.length == 0) {
          IMarker marker = markerHandle.createMarker(modelMarker.getMarkerId());
          marker.setAttributes(new String[] {
              MarkerProblem.ATTRIB_DESCRIPTION,
              MarkerProblem.ATTRIB_PATH,
              MarkerProblem.ATTRIB_SOURCE_OPENER }, getMarkerAttributes());
        }
      }
      else {
        markerHandle.deleteMarkers(modelMarker.getMarkerId(), true, IResource.DEPTH_ZERO);
      }
    }
    catch (CoreException e) {
      new Logger(ICharacterFreebiesPluginConstants.PLUGIN_ID).error(Messages.ResourceModelMarker_ErrorWhileMarking, e);
    }
  }

  private Object[] getMarkerAttributes() {
    IResource resource = (IResource) markerHandle.getAdapter(IResource.class);
    IContainer container = resource.getParent();
    String displayName = new ModelExtensionPoint().getDisplayConfiguration(resource).getDisplayName();
    CharacterDisplayNameProvider characterNameProvider = new CharacterDisplayNameProvider(container);
    String modelName = new ModelDisplayNameProvider(displayName, characterNameProvider).getDisplayName();
    return new Object[] {
        modelMarker.getDescription(characterNameProvider.getDisplayName()),
        modelName,
        CharacterModelEditorOpener.ID };
  }

  @Override
  public void dispose() {
    if (changeableModel != null) {
      changeableModel.removeChangeListener(markListener);
    }
  }
}