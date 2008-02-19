package net.sf.anathema.campaign.plot.report;

import java.io.OutputStream;

import net.sf.anathema.basics.pdfexport.writer.AbstractReportRunner;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;
import net.sf.anathema.campaign.plot.report.model.IPlotElement;

import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IEditorPart;

public class PlotReportRunner extends AbstractReportRunner<IPlotElement> {

  private final IPlotElement rootElement;

  public PlotReportRunner(IOutputStreamFactory outputStreamFactory, IPlotElement rootElement) {
    super(outputStreamFactory);
    this.rootElement = rootElement;
  }

  @Override
  protected IRunnableWithProgress createRunnable(
      IEditorPart editorPart,
      OutputStream outputStream,
      IExportItem<IPlotElement> exportItem) {
    return new PlotReportRunnable(exportItem, outputStream);
  }
}