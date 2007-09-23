package net.sf.anathema.character.freebies.attributes.mark;


public class AttributeMarking {
//
//  private static final String UNSPENT_FREEBIES_MARKER = "net.sf.anathema.markers.unspent.attribute.freebies";
//
//  public void mark() {
//    //$NON-NLS-1$
//    ModelCache.getInstance().getModel(getModelIdentifier()).addChangeListener(new IChangeListener() {
//
//      @Override
//      public void stateChanged() {
//        markFile();
//      }
//    });
//    markFile();
//  }
//
//  private void markFile() {
//    IFile file = getFile();
//    if (!file.exists()) {
//      return;
//    }
//    PriorityGroup priority = PriorityGroup.Primary;
//    Dots dots = new AttributePointCalculator(context.getCollection(), context.getTraitGroups()).dotsFor(priority);
//    boolean warning = creditByPriority.get(priority) > dots.spentTotally();
//    try {
//      if (warning) {
//        IMarker[] markers = file.findMarkers(UNSPENT_FREEBIES_MARKER, true, IResource.DEPTH_ZERO);
//        if (markers.length == 0) {
//          file.createMarker(UNSPENT_FREEBIES_MARKER);
//        }
//      }
//      else {
//        file.deleteMarkers(UNSPENT_FREEBIES_MARKER, true, IResource.DEPTH_ZERO);
//      }
//    }
//    catch (CoreException e) {
//    }
//  }
}