package net.sf.anathema.campaign.note.report;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import net.sf.anathema.basics.item.IPersistableEditorInput;
import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.pdfexport.writer.IReportWriter;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IEditorPart;

import com.lowagie.text.DocumentException;

public final class NoteReportRunnable implements IRunnableWithProgress {
  private final IEditorPart editorPart;
  private final OutputStream outputStream;
  private final IReportWriter<ITitledText> writer;

  public NoteReportRunnable(IEditorPart editorPart, OutputStream outputStream, IReportWriter<ITitledText> writer) {
    this.editorPart = editorPart;
    this.outputStream = outputStream;
    this.writer = writer;
  }

  @Override
  public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
    try {
      monitor.beginTask("Creating note PDF", 1 + writer.getTaskCount());
      monitor.subTask("Preparing note");
      ITitledText character = createNote();
      monitor.worked(1);
      writer.write(monitor, character, outputStream);
    }
    catch (DocumentException e) {
      throw new InvocationTargetException(e);
    }
    finally {
      monitor.done();
    }
  }

  private ITitledText createNote() {
    IPersistableEditorInput<ITitledText> editorInput = (IPersistableEditorInput<ITitledText>) editorPart.getEditorInput();
    return editorInput.getItem();
  }
}