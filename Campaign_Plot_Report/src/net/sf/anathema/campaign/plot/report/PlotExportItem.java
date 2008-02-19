package net.sf.anathema.campaign.plot.report;

import net.sf.anathema.basics.pdfexport.writer.IExportItem;
import net.sf.anathema.campaign.plot.persistence.PlotPersister;
import net.sf.anathema.campaign.plot.report.model.IPlotElement;
import net.sf.anathema.campaign.plot.report.model.PlotElement;
import net.sf.anathema.campaign.plot.repository.IPlotPart;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;

public class PlotExportItem implements IExportItem<IPlotElement> {

  private PlotElement rootElement;

  public PlotExportItem(IContainer container) throws PersistenceException, CoreException {
    IPlotPart root = new PlotPersister().load(container);
    this.rootElement = new PlotElement(root, container);
  }

  @Override
  public IPlotElement createItem() {
    return rootElement;
  }

  @Override
  public String getPrintName() {
    return rootElement.getContent().getName().getText();
  }
}