package net.sf.anathema.character.report.pdf;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;
import net.sf.anathema.basics.swt.file.IStreamResult;
import net.sf.anathema.character.report.internal.pdf.CharacterReportRunnable;
import net.sf.anathema.character.report.internal.pdf.Messages;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;

public class CharacterReportRunner {

  private final Logger logger = new Logger("net.sf.anathema.character.report"); //$NON-NLS-1$
  private final IOutputStreamFactory streamFactory;
  private final ICharacterReportWriter writer;

  public CharacterReportRunner(IOutputStreamFactory streamFactory, ICharacterReportWriter writer) {
    this.streamFactory = streamFactory;
    this.writer = writer;
  }

  public void runWriting(Shell shell, final IEditorPart editorPart, IRunnableContext runnableContext) {
    OutputStream outputStream = null;
    IStreamResult streamResult = null;
    try {
      streamResult = streamFactory.create(shell);
      if (streamResult == null) {
        return;
      }
      outputStream = streamResult.createStream();
      runnableContext.run(true, false, new CharacterReportRunnable(editorPart, outputStream, writer));
    }
    catch (InvocationTargetException e) {
      indicateError(shell, e.getCause());
    }
    catch (Exception e) {
      indicateError(shell, e);
    }
    finally {
      IOUtilities.close(outputStream);
      if (streamResult != null) {
        streamResult.openResult();
      }
    }
  }

  private void indicateError(Shell shell, Throwable cause) {
    if (cause instanceof FileNotFoundException) {
      MessageDialog.openError(
          shell,
          Messages.CharacterReportRunner_Title,
          Messages.CharacterReportRunner_FileInUseMessage);
    }
    else {
      logger.error(Messages.CharacterReportRunner_CharacterPdfErrorMessage, cause);
      MessageDialog.openError(
          shell,
          Messages.CharacterReportRunner_Title,
          Messages.CharacterReportRunner_CharacterPdfErrorMessage);
    }
  }
}