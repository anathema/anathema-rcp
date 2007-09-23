package net.sf.anathema.character.sheet.print;

import java.io.FileOutputStream;
import java.io.OutputStream;

import net.disy.commons.core.io.IOUtilities;

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
      outputStream = new FileOutputStream("C:/test.pdf");
      new Printer().printReport(outputStream);
    }
    catch (Exception e) {
      
    }
    finally {
      IOUtilities.close(outputStream);
    }
    return null;
  }
}