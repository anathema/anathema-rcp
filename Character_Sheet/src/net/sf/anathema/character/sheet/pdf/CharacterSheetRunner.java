package net.sf.anathema.character.sheet.pdf;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;

import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;

public class CharacterSheetRunner {

  private Logger logger = new Logger("net.sf.anathema.character.sheet"); //$NON-NLS-1$
  private final IOutputStreamFactory streamFactory;
  private final ICharacterSheetWriter writer;

  public CharacterSheetRunner(IOutputStreamFactory streamFactory, ICharacterSheetWriter writer) {
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
      runnableContext.run(true, false, new CharacterSheetRunnable(editorPart, outputStream, writer));
    }
    catch (InvocationTargetException e) {
      logger.error(Messages.CharacterSheetHandler_CharacterPdfErrorMessage, e.getCause());

    }
    catch (Exception e) {
      logger.error(Messages.CharacterSheetHandler_CharacterPdfErrorMessage, e.getCause());

    }
    finally {
      IOUtilities.close(outputStream);
    }
  }
}