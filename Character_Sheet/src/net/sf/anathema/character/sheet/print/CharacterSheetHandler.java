package net.sf.anathema.character.sheet.print;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.swt.file.FileChoosing;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.sheet.content.ICharacter;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

public class CharacterSheetHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
    OutputStream outputStream = null;
    try {
      IModelIdentifier identifier = (IModelIdentifier) editorPart.getEditorInput().getAdapter(IModelIdentifier.class);
      ICharacterId characterId = identifier.getCharacterId();
      ICharacter character = new Character(characterId, ModelCache.getInstance());
      File file = FileChoosing.getPdfFile(null, HandlerUtil.getActiveShell(event));
      if (file == null) {
        return null;
      }
      outputStream = new FileOutputStream(file);
      new CharacterSheetPrinter().printReport(character, outputStream);
    }
    catch (Exception e) {

    }
    finally {
      IOUtilities.close(outputStream);
    }
    return null;
  }
}