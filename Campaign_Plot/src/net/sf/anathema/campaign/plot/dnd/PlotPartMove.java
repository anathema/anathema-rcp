package net.sf.anathema.campaign.plot.dnd;

import net.sf.anathema.campaign.plot.repository.IPlotPart;

public class PlotPartMove {

  private final IPlotPart sourcePart;
  private final IPlotPart targetPart;

  public PlotPartMove(IPlotPart sourcePart, IPlotPart targetPart) {
    this.sourcePart = sourcePart;
    this.targetPart = targetPart;
  }

  public boolean isValid(RelativeLocation location) {
    if (sourcePart.getRoot() != targetPart.getRoot()) {
      return false;
    }
    return true;
  }

  public void moveTo(RelativeLocation location) {
    IPlotPart parent = sourcePart.getParent();
    int sourcePartIndex = parent.indexOf(sourcePart);
    int targetPartIndex = parent.indexOf(targetPart);
    int targetIndex = location == RelativeLocation.Before ? targetPartIndex - 1 : targetPartIndex;
    if (sourcePartIndex > targetPartIndex) {
      targetIndex++;
    }
    parent.moveChild(sourcePart, targetIndex);
  }
}