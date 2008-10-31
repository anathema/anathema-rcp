package net.sf.anathema.campaign.plot.report;

import java.io.OutputStream;

import net.sf.anathema.basics.pdfexport.writer.AbstractReportRunnable;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;
import net.sf.anathema.campaign.plot.report.model.IPlotElement;

public class PlotReportRunnable extends AbstractReportRunnable<IPlotElement> {

  private final IExportItem<IPlotElement> exportItem;

  public PlotReportRunnable(IExportItem<IPlotElement> exportItem, OutputStream outputStream) {
    super(outputStream, new MultiColumnSeriesReportWriter());
    this.exportItem = exportItem;
  }

  @Override
  protected IPlotElement createItem() {
    return exportItem.createItem();
  }
}