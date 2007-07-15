package net.sf.anathema.campaign.plot.repository;

import java.io.IOException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

public interface IPlotElementViewElement extends IViewElement {

  public IPlotPart getPlotElement();

  public IPlotElementViewElement[] getChildren();

  public void delete() throws CoreException, IOException;

  public boolean isPartOf(IContainer parent);

  public void closeRelatedEditors(IWorkbenchPage page) throws PartInitException;
}