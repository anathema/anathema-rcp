package net.sf.anathema.character.sheet.print;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.swt.file.FileChoosing;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.sheet.content.ICharacter;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

public class CharacterSheetHandler extends AbstractHandler {
  private Logger logger = new Logger("net.sf.anathema.character.sheet"); //$NON-NLS-1$

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    Shell shell = HandlerUtil.getActiveShell(event);
    OutputStream outputStream = null;
    try {
      outputStream = createOutputStream(shell);
      if (outputStream == null) {
        return null;
      }
      IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
      ICharacter character = createCharacter(editorPart);
      new CharacterSheetPrinter().printReport(character, outputStream);
    }
    catch (Exception e) {
      logger.error(Messages.CharacterSheetHandler_CharacterPdfErrorMessage, e);
      
    }
    finally {
      IOUtilities.close(outputStream);
    }
    return null;
  }

  private ICharacter createCharacter(IEditorPart editorPart) {
    IEditorInput editorInput = editorPart.getEditorInput();
    IModelIdentifier identifier = (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
    return new Character(identifier.getCharacterId(), ModelCache.getInstance());
  }

  private OutputStream createOutputStream(Shell shell) throws FileNotFoundException {
    File file = FileChoosing.getPdfFile(null, shell);
    if (file == null) {
      return null;
    }
    return new FileOutputStream(file);
  }
}