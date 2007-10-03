package net.sf.anathema.character.freebies.attributes.mark;

import net.disy.commons.core.model.IChangeableModel;
import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.eclipse.resource.IMarkerHandle;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;
import net.sf.anathema.character.freebies.attributes.calculation.Dots;
import net.sf.anathema.character.freebies.attributes.calculation.IAttributeCreditCollection;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class AttributeResourceMarker implements IDisposable {
  private static final String UNSPENT_FREEBIES_MARKER = "net.sf.anathema.markers.unspent.attribute.freebies"; //$NON-NLS-1$
  private final ITraitCollectionContext context;
  private final IMarkerHandle markerHandle;
  private final IChangeListener markListener = new IChangeListener() {
    @Override
    public void stateChanged() {
      markFile();
    }
  };
  private final IAttributeCreditCollection creditCollection;
  private final IChangeableModel changeableModel;
  private final PriorityGroup priority;

  public AttributeResourceMarker(
      IAttributeCreditCollection creditCollection,
      ITraitCollectionContext context,
      IChangeableModel changeableModel,
      IMarkerHandle modelResource,
      PriorityGroup priority) {
    this.creditCollection = creditCollection;
    this.changeableModel = changeableModel;
    this.markerHandle = modelResource;
    this.context = context;
    this.priority = priority;
  }

  public void mark() {
    changeableModel.addChangeListener(markListener);
    markFile();
  }

  private void markFile() {
    if (!markerHandle.exists()) {
      return;
    }
    Dots dots = new AttributePointCalculator(context.getCollection(), context.getTraitGroups()).dotsFor(priority);
    boolean warning = creditCollection.getCredit(priority) > dots.spentTotally();
    try {
      if (warning) {
        IMarker[] markers = markerHandle.findMarkers(UNSPENT_FREEBIES_MARKER, true, IResource.DEPTH_ZERO);
        if (markers.length == 0) {
          markerHandle.createMarker(UNSPENT_FREEBIES_MARKER);
        }
      }
      else {
        markerHandle.deleteMarkers(UNSPENT_FREEBIES_MARKER, true, IResource.DEPTH_ZERO);
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