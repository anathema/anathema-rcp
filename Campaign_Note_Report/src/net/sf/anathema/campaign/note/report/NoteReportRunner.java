package net.sf.anathema.campaign.note.report;

import net.sf.anathema.basics.pdfexport.IReportRunner;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;

import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;

public class NoteReportRunner implements IReportRunner {
  private final IOutputStreamFactory outputStreamFactory;

  public NoteReportRunner(IOutputStreamFactory outputStreamFactory) {
    this.outputStreamFactory = outputStreamFactory;
  }

  @Override
  public void runWriting(Shell shell, IEditorPart editorPart, IRunnableContext runnableContext) {
    // TODO Case 31: Notes in PDF drucken.
  }
}