package net.sf.anathema.campaign.plot.item;

public interface IPlotElementContainerListener {

  public void childRemoved(IPlotElement removal);

  public void childAdded(IPlotElementContainer container, IPlotElement newChild);

  public void childMoved(IPlotElement element, int newIndex);

  public void childInserted(IPlotElement insertion, IPlotElementContainer parentContainer, int index);
}