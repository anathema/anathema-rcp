package net.sf.anathema.character.freebies.attributes.mark;

import net.disy.commons.core.model.IChangeableModel;
import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.eclipse.resource.IMarkerHandle;
import net.sf.anathema.character.freebies.attributes.calculation.IAttributeCreditCollection;
import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class AttributeResourceMarker implements IDisposable {
  private final IChangeListener markListener = new IChangeListener() {
    @Override
    public void stateChanged() {
      markFile();
    }
  };
  private final ITotalDotsSpent dotsSpent;
  private final IMarkerHandle markerHandle;
  private final IAttributeCreditCollection creditCollection;
  private final IChangeableModel changeableModel;
  private final AttributeGroupMarkerId markerId;

  public AttributeResourceMarker(
      IAttributeCreditCollection creditCollection,
      ITotalDotsSpent dotsSpent,
      IChangeableModel changeableModel,
      IMarkerHandle markerHandle,
      AttributeGroupMarkerId markerId) {
    this.creditCollection = creditCollection;
    this.changeableModel = changeableModel;
    this.markerHandle = markerHandle;
    this.dotsSpent = dotsSpent;
    this.markerId = markerId;
  }

  public void mark() {
    changeableModel.addChangeListener(markListener);
    markFile();
  }

  private void markFile() {
    if (!markerHandle.exists()) {
      return;
    }
    boolean warning = creditCollection.getCredit(markerId.getPriority()) > dotsSpent.get(markerId.getPriority());
    try {
      if (warning) {
        IMarker[] markers = markerHandle.findMarkers(markerId.getId(), true, IResource.DEPTH_ZERO);
        if (markers.length == 0) {
          markerHandle.createMarker(markerId.getId());
        }
      }
      else {
        markerHandle.deleteMarkers(markerId.getId(), true, IResource.DEPTH_ZERO);
      }
    }
    catch (CoreException e) {
    }
  }

  @Override
  public void dispose() {
    if (changeableModel != null) {
      changeableModel.removeChangeListener(markListener);
    }
  }
}