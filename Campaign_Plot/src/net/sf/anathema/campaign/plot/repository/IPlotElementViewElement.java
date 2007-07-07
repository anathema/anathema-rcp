package net.sf.anathema.campaign.plot.repository;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

public interface IPlotElementViewElement extends IViewElement {

  public IPlotPart getPlotElement();

  public IPlotElementViewElement[] getChildren();

  public void delete() throws CoreException, IOException;
}