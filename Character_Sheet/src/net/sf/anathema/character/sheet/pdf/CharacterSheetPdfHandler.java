package net.sf.anathema.character.sheet.pdf;

import net.sf.anathema.basics.swt.file.PdfFileOutputStreamFactory;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

public class CharacterSheetPdfHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    Shell shell = HandlerUtil.getActiveShell(event);
    IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
    PdfFileOutputStreamFactory outputStreamFactory = new PdfFileOutputStreamFactory();
    CharacterSheetPdfWriter characterSheetWriter = new CharacterSheetPdfWriter();
    new CharacterSheetHandler(outputStreamFactory, characterSheetWriter).writeToOutput(shell, editorPart);
    return null;
  }
}