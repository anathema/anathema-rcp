package net.sf.anathema.character.report.internal.wizard;

import java.io.OutputStream;

import net.sf.anathema.basics.pdfexport.writer.AbstractReportRunnable;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;
import net.sf.anathema.basics.pdfexport.writer.IReportWriter;
import net.sf.anathema.character.core.character.ICharacter;

public final class CharacterReportRunnable extends AbstractReportRunnable<ICharacter> {
  private final IExportItem<ICharacter> exportItem;

  public CharacterReportRunnable(
      IExportItem<ICharacter> exportItem,
      OutputStream outputStream,
      IReportWriter<ICharacter> writer) {
    super(outputStream, writer);
    this.exportItem = exportItem;
  }

  @Override
  protected ICharacter createItem() {
    return exportItem.createItem();
  }
}