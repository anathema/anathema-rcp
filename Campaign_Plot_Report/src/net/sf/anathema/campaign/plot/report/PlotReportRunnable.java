package net.sf.anathema.campaign.plot.report;

import java.io.OutputStream;

import net.sf.anathema.basics.pdfexport.writer.AbstractReportRunnable;
import net.sf.anathema.campaign.plot.report.model.IPlotElement;

import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IEditorPart;

public class PlotReportRunnable extends AbstractReportRunnable<IPlotElement> implements IRunnableWithProgress {

  private final IEditorPart editorPart;

  public PlotReportRunnable(IEditorPart editorPart, OutputStream outputStream) {
    super(outputStream, new MultiColumnSeriesReportWriter());
    this.editorPart = editorPart;
  }

  @Override
  protected IPlotElement createItem() {
    // TODO Hier muss das Root PlotElement erzeugt werden
    return null;
  }
}