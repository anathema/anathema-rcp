package net.sf.anathema.character.freebies.attributes.mark;

import net.disy.commons.core.model.IChangeableModel;
import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.eclipse.resource.IMarkerHandle;
import net.sf.anathema.basics.repository.problems.MarkerProblem;
import net.sf.anathema.character.core.resource.CharacterPrintNameProvider;
import net.sf.anathema.character.core.resource.ModelResourceNameProvider;
import net.sf.anathema.character.freebies.attributes.plugin.IAttributeFreebiesConstants;
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
          marker.setAttributes(
              new String[] { MarkerProblem.ATTRIB_DESCRIPTION, MarkerProblem.ATTRIB_PATH },
              getMarkerAttributes());
        }
      }
      else {
        markerHandle.deleteMarkers(modelMarker.getMarkerId(), true, IResource.DEPTH_ZERO);
      }
    }
    catch (CoreException e) {
      new Logger(IAttributeFreebiesConstants.PLUGIN_ID).error(Messages.ResourceModelMarker_ErrorWhileMarking, e);
    }
  }

  private String[] getMarkerAttributes() {
    IResource resource = (IResource) markerHandle.getAdapter(IResource.class);
    IContainer container = resource.getParent();
    String characterName = new CharacterPrintNameProvider().getPrintName(container, container.getName());
    String modelName = new ModelResourceNameProvider().getResourceName(resource, characterName);
    return new String[] { modelMarker.getDescription(characterName), modelName };
  }

  @Override
  public void dispose() {
    if (changeableModel != null) {
      changeableModel.removeChangeListener(markListener);
    }
  }
}