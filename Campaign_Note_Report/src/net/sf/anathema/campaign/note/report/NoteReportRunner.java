package net.sf.anathema.campaign.note.report;

import java.io.OutputStream;

import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.pdfexport.writer.AbstractReportRunner;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;

import org.eclipse.jface.operation.IRunnableWithProgress;

public class NoteReportRunner extends AbstractReportRunner<ITitledText> {

  public NoteReportRunner(IOutputStreamFactory outputStreamFactory) {
    super(outputStreamFactory);
  }

  @Override
  protected IRunnableWithProgress createRunnable(
      IExportItem<ITitledText> exportItem,
      OutputStream outputStream) {
    return new NoteReportRunnable(exportItem, outputStream, new NoteReportWriter());
  }
}