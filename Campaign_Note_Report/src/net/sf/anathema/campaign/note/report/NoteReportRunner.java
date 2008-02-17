package net.sf.anathema.campaign.note.report;

import java.io.OutputStream;

import net.sf.anathema.basics.pdfexport.writer.AbstractReportRunner;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;

import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IEditorPart;

public class NoteReportRunner extends AbstractReportRunner {

  public NoteReportRunner(IOutputStreamFactory outputStreamFactory) {
    super(outputStreamFactory);
  }

  @Override
  protected IRunnableWithProgress createRunnable(IEditorPart editorPart, OutputStream outputStream) {
    return new NoteReportRunnable(editorPart, outputStream, new NoteReportWriter());
  }
}