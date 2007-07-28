package net.sf.anathema.campaign.plot.repository;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.resources.IContainer;

public interface IPlotElementViewElement extends IViewElement {

  public IPlotPart getPlotElement();

  public IPlotElementViewElement[] getChildren();

  public boolean isPartOf(IContainer parent);
}