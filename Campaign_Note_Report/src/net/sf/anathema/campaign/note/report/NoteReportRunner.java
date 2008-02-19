package net.sf.anathema.campaign.note.report;

import java.io.OutputStream;

import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.pdfexport.writer.AbstractReportRunner;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;

import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IEditorPart;

public class NoteReportRunner extends AbstractReportRunner<ITitledText> {

  public NoteReportRunner(IOutputStreamFactory outputStreamFactory) {
    super(outputStreamFactory);
  }

  @Override
  protected IRunnableWithProgress createRunnable(
      IEditorPart editorPart,
      OutputStream outputStream,
      IExportItem<ITitledText> exportItem) {
    return new NoteReportRunnable(editorPart, outputStream, new NoteReportWriter());
  }
}