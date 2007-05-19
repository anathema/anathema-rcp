package net.sf.anathema.campaign.plot.item;

import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.textualdescription.ITextualDescription;

public class Series implements ISeries {

  private final PlotModel plotModel = new PlotModel();

  public IPlotModel getPlot() {
    return plotModel;
  }

  public void setPrintNameAdjuster(IObjectValueChangedListener<String> adjuster) {
    ITextualDescription rootName = plotModel.getRootElement().getDescription().getName();
    rootName.addTextChangedListener(adjuster);
  }

  public void addDirtyListener(IChangeListener changeListener) {
    plotModel.addDirtyListener(changeListener);
  }

  public boolean isDirty() {
    return plotModel.isDirty();
  }

  public void setClean() {
    plotModel.setClean();
  }

  public void removeDirtyListener(IChangeListener changeListener) {
    plotModel.removeDirtyListener(changeListener);
  }
}