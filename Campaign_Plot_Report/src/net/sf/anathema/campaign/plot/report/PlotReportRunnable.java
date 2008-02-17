package net.sf.anathema.campaign.plot.report;

import java.io.OutputStream;

import net.sf.anathema.basics.pdfexport.writer.AbstractReportRunnable;
import net.sf.anathema.campaign.plot.persistence.PlotPersister;
import net.sf.anathema.campaign.plot.report.model.IPlotElement;
import net.sf.anathema.campaign.plot.report.model.PlotElement;
import net.sf.anathema.campaign.plot.repository.IPlotPart;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
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
    IResource resource = (IResource) editorPart.getEditorInput().getAdapter(IResource.class);
    IContainer parent = resource.getParent();
    try {
      IPlotPart root = new PlotPersister().load(parent);
      return new PlotElement(root, parent);
    }
    catch (Exception e) {
      return null;
    }
  }
}