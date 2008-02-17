package net.sf.anathema.campaign.plot.report;

import java.io.OutputStream;

import net.sf.anathema.basics.pdfexport.writer.AbstractReportRunnable;
import net.sf.anathema.campaign.plot.report.model.IPlotElement;

import org.eclipse.jface.operation.IRunnableWithProgress;

public class PlotReportRunnable extends AbstractReportRunnable<IPlotElement> implements IRunnableWithProgress {

  private final IPlotElement rootElement;

  public PlotReportRunnable(IPlotElement plotElement, OutputStream outputStream) {
    super(outputStream, new MultiColumnSeriesReportWriter());
    rootElement = plotElement;
  }

  @Override
  protected IPlotElement createItem() {
    return rootElement;
  }
}