package net.sf.anathema.character.report.internal.wizard;

import java.io.OutputStream;

import net.sf.anathema.basics.pdfexport.writer.AbstractReportRunner;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;
import net.sf.anathema.basics.pdfexport.writer.IReportWriter;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;
import net.sf.anathema.character.core.character.ICharacter;

import org.eclipse.jface.operation.IRunnableWithProgress;

public class CharacterReportRunner extends AbstractReportRunner<ICharacter> {

  private final IReportWriter<ICharacter> writer;

  public CharacterReportRunner(IOutputStreamFactory streamFactory, IReportWriter<ICharacter> writer) {
    super(streamFactory);
    this.writer = writer;
  }

  @Override
  protected IRunnableWithProgress createRunnable(
      IExportItem<ICharacter> exportItem,
      OutputStream outputStream) {
    return new CharacterReportRunnable(exportItem, outputStream, writer);
  }
}