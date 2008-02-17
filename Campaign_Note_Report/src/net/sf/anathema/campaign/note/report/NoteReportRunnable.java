package net.sf.anathema.campaign.note.report;

import java.io.OutputStream;

import net.sf.anathema.basics.item.IPersistableEditorInput;
import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.pdfexport.writer.AbstractReportRunnable;
import net.sf.anathema.basics.pdfexport.writer.IReportWriter;

import org.eclipse.ui.IEditorPart;

public final class NoteReportRunnable extends AbstractReportRunnable<ITitledText> {
  private final IEditorPart editorPart;

  public NoteReportRunnable(IEditorPart editorPart, OutputStream outputStream, IReportWriter<ITitledText> writer) {
    super(outputStream, writer);
    this.editorPart = editorPart;
  }

  @Override
  protected ITitledText createItem() {
    return ((IPersistableEditorInput<ITitledText>) editorPart.getEditorInput()).getItem();
  }
}