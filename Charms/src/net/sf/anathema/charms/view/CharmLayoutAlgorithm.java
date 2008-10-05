package net.sf.anathema.charms.view;

import org.eclipse.zest.layouts.algorithms.AbstractLayoutAlgorithm;
import org.eclipse.zest.layouts.dataStructures.InternalNode;
import org.eclipse.zest.layouts.dataStructures.InternalRelationship;

public class CharmLayoutAlgorithm extends AbstractLayoutAlgorithm {

  public CharmLayoutAlgorithm(int styles) {
    super(styles);
  }

  @Override
  protected void preLayoutAlgorithm(
      InternalNode[] entitiesToLayout,
      InternalRelationship[] relationshipsToConsider,
      double x,
      double y,
      double width,
      double height) {
    // hier doll initialisieren
    // TODO Auto-generated method stub
  }

  @Override
  protected void applyLayoutInternal(
      InternalNode[] entitiesToLayout,
      InternalRelationship[] relationshipsToConsider,
      double boundsX,
      double boundsY,
      double boundsWidth,
      double boundsHeight) {
  }

  @Override
  protected void postLayoutAlgorithm(InternalNode[] entitiesToLayout, InternalRelationship[] relationshipsToConsider) {
    updateLayoutLocations(entitiesToLayout);
    fireProgressEvent (1, 1);
  }

  @Override
  protected int getCurrentLayoutStep() {
    return 0;
  }

  @Override
  protected int getTotalNumberOfLayoutSteps() {
    return 1;
  }

  @Override
  protected boolean isValidConfiguration(boolean asynchronous, boolean continueous) {
    if (asynchronous && continueous) {
      return false;
    }
    else if (asynchronous && !continueous) {
      return true;
    }
    else if (!asynchronous && continueous) {
      return false;
    }
    else if (!asynchronous && !continueous) {
      return true;
    }

    return false;
  }

  @Override
  public void setLayoutArea(double x, double y, double width, double height) {
    throw new RuntimeException();
  }
}