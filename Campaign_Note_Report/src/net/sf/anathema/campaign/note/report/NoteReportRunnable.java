package net.sf.anathema.campaign.note.report;

import java.io.OutputStream;

import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.pdfexport.writer.AbstractReportRunnable;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;
import net.sf.anathema.basics.pdfexport.writer.IReportWriter;

public final class NoteReportRunnable extends AbstractReportRunnable<ITitledText> {

  private final IExportItem<ITitledText> exportItem;

  public NoteReportRunnable(
      IExportItem<ITitledText> exportItem,
      OutputStream outputStream,
      IReportWriter<ITitledText> writer) {
    super(outputStream, writer);
    this.exportItem = exportItem;
  }

  @Override
  protected ITitledText createItem() {
    return exportItem.createItem();
  }
}