package net.sf.anathema.campaign.note.report;

import net.sf.anathema.basics.importexport.IFileSelectionPageMessages;
import net.sf.anathema.basics.pdfexport.AbstractPdfExportWizard;
import net.sf.anathema.basics.pdfexport.IReportRunner;
import net.sf.anathema.basics.pdfexport.message.ExportMessages;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;

import org.eclipse.ui.IEditorPart;

public class PlotExportWizard extends AbstractPdfExportWizard {

  @Override
  protected IFileSelectionPageMessages createMessage() {
    return new ExportMessages(Messages.PlotExportWizard_Title);
  }

  @Override
  protected IReportRunner createRunner(IOutputStreamFactory outputStreamFactory) {
    return new PlotReportRunner(outputStreamFactory);
  }

  @Override
  protected String getSuggestedName(IEditorPart editor) {
    // TODO Case 32: Namen für Plot ermitteln
    return "Plot";
  }
}