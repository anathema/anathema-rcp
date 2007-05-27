package net.sf.anathema.campaign.plot.dnd;

import net.sf.anathema.campaign.plot.repository.IPlotPart;

public class PlotPartMove {

  private final IPlotPart sourcePart;
  private final IPlotPart targetPart;

  public PlotPartMove(IPlotPart sourcePart, IPlotPart targetPart) {
    this.sourcePart = sourcePart;
    this.targetPart = targetPart;
  }

  public boolean isValid() {
    if (sourcePart.getRoot() != targetPart.getRoot()) {
      return false;
    }
    if (sourcePart.getPlotUnit() == targetPart.getPlotUnit()) {
      return true;
    }
    return false;
  }

  public void moveTo(RelativeLocation location) {
    if (sourcePart == targetPart) {
      return;
    }
    IPlotPart sourceParent = sourcePart.getParent();
    IPlotPart targetParent = targetPart.getParent();
    if (sourceParent == targetParent) {
      moveSibling(location, sourceParent);
    }
    else {
      moveCousin(location, sourceParent, targetParent);
    }
  }

  private void moveCousin(RelativeLocation location, IPlotPart sourceParent, IPlotPart targetParent) {
    sourceParent.removeChild(sourcePart);
    int targetPartIndex = targetParent.indexOf(targetPart);
    int targetIndex = location == RelativeLocation.Before ? targetPartIndex : targetPartIndex + 1;
    targetParent.addChild(sourcePart, targetIndex);
  }

  private void moveSibling(RelativeLocation location, IPlotPart parent) {
    int sourcePartIndex = parent.indexOf(sourcePart);
    int targetPartIndex = parent.indexOf(targetPart);
    int targetIndex = location == RelativeLocation.Before ? targetPartIndex - 1 : targetPartIndex;
    if (sourcePartIndex > targetPartIndex) {
      targetIndex++;
    }
    parent.moveChild(sourcePart, targetIndex);
  }
}