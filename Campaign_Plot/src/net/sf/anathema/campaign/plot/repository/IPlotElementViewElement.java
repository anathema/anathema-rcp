package net.sf.anathema.campaign.plot.repository;

import java.io.IOException;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IWorkbenchPage;

public interface IPlotElementViewElement extends IViewElement {

  public IPlotPart getPlotElement();

  public IPlotElementViewElement[] getChildren();

  public boolean isPartOf(IContainer parent);

  public void delete(IWorkbenchPage page) throws CoreException, IOException;
}