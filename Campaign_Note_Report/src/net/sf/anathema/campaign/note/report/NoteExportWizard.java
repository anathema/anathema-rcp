package net.sf.anathema.campaign.note.report;

import net.sf.anathema.basics.importexport.IFileSelectionPageMessages;
import net.sf.anathema.basics.pdfexport.AbstractPdfExportWizard;
import net.sf.anathema.basics.pdfexport.IReportRunner;
import net.sf.anathema.basics.pdfexport.message.ExportMessages;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;

import org.eclipse.ui.IEditorPart;

public class NoteExportWizard extends AbstractPdfExportWizard {

  @Override
  protected IFileSelectionPageMessages createMessage() {
    return new ExportMessages(Messages.NoteExportWizard_Title);
  }

  @Override
  protected IReportRunner createRunner(IOutputStreamFactory outputStreamFactory) {
    return new NoteReportRunner(outputStreamFactory);
  }

  @Override
  protected String getSuggestedName(IEditorPart editor) {
    return editor.getEditorInput().getName();
  }
}