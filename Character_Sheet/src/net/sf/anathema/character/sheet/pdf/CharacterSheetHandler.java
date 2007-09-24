package net.sf.anathema.character.sheet.pdf;

import java.io.OutputStream;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.sheet.content.ICharacter;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

public class CharacterSheetHandler {

  private Logger logger = new Logger("net.sf.anathema.character.sheet"); //$NON-NLS-1$
  private final IOutputStreamFactory streamFactory;
  private final ICharacterSheetWriter writer;

  public CharacterSheetHandler(IOutputStreamFactory streamFactory, ICharacterSheetWriter writer) {
    this.streamFactory = streamFactory;
    this.writer = writer;
  }

  public void writeToOutput(Shell shell, IEditorPart editorPart) {
    OutputStream outputStream = null;
    try {
      outputStream = streamFactory.create(shell);
      if (outputStream == null) {
        return;
      }
      ICharacter character = createCharacter(editorPart);
      writer.write(character, outputStream);
    }
    catch (Exception e) {
      logger.error(Messages.CharacterSheetHandler_CharacterPdfErrorMessage, e);

    }
    finally {
      IOUtilities.close(outputStream);
    }
  }

  private ICharacter createCharacter(IEditorPart editorPart) {
    IEditorInput editorInput = editorPart.getEditorInput();
    IModelIdentifier identifier = (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
    return new Character(identifier.getCharacterId(), ModelCache.getInstance());
  }
}