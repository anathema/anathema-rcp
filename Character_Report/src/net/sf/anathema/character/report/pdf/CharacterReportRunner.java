package net.sf.anathema.character.report.pdf;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;

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
    try {
      outputStream = streamFactory.create(shell);
      if (outputStream == null) {
        return;
      }
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