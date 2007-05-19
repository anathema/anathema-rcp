package net.sf.anathema.campaign.plot.item;

public interface IPlotModel {

  public IPlotElement getRootElement();

  public IPlotElement getParentElement(IPlotElement element);

  public boolean isDirty();

  public void setClean();
}