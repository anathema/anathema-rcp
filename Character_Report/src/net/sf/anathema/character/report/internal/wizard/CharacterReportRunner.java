package net.sf.anathema.character.report.internal.wizard;

import java.io.OutputStream;

import net.sf.anathema.basics.pdfexport.writer.AbstractReportRunner;
import net.sf.anathema.basics.pdfexport.writer.IReportWriter;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;
import net.sf.anathema.character.core.character.ICharacter;

import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IEditorPart;

public class CharacterReportRunner extends AbstractReportRunner {

  private final IReportWriter<ICharacter> writer;

  public CharacterReportRunner(IOutputStreamFactory streamFactory, IReportWriter<ICharacter> writer) {
    super(streamFactory);
    this.writer = writer;
  }

  @Override
  protected IRunnableWithProgress createRunnable(final IEditorPart editorPart, OutputStream outputStream) {
    return new CharacterReportRunnable(editorPart, outputStream, writer);
  }
}