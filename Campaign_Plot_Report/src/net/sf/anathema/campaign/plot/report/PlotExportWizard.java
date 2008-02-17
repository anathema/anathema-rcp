package net.sf.anathema.campaign.plot.report;

import net.sf.anathema.basics.importexport.IFileSelectionPageMessages;
import net.sf.anathema.basics.pdfexport.AbstractPdfExportWizard;
import net.sf.anathema.basics.pdfexport.IReportRunner;
import net.sf.anathema.basics.pdfexport.message.ExportMessages;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;
import net.sf.anathema.campaign.plot.report.model.IPlotElement;
import net.sf.anathema.campaign.plot.report.model.PlotElement;

import org.eclipse.ui.IEditorPart;

public class PlotExportWizard extends AbstractPdfExportWizard {

  private IPlotElement rootElement;

  @Override
  protected IFileSelectionPageMessages createMessage() {
    return new ExportMessages(Messages.PlotExportWizard_Title);
  }

  @Override
  protected IReportRunner createRunner(IOutputStreamFactory outputStreamFactory) {
    return new PlotReportRunner(outputStreamFactory, rootElement);
  }

  @Override
  protected String getSuggestedName(IEditorPart editor) {
    rootElement = PlotElement.getRootElement(editor);
    return rootElement.getContent().getName().getText();
  }
}