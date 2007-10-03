package net.sf.anathema.character.sheet.pdf;

import net.sf.anathema.basics.swt.file.PdfFileOutputStreamFactory;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.description.ICharacterDescription;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

public class CharacterSheetPdfHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    Shell shell = HandlerUtil.getActiveShell(event);
    IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
    PdfFileOutputStreamFactory outputStreamFactory = new PdfFileOutputStreamFactory(getSuggestedName(editorPart));
    CharacterSheetPdfWriter characterSheetWriter = new CharacterSheetPdfWriter();
    IRunnableContext runnableContext = HandlerUtil.getActiveWorkbenchWindow(event);
    CharacterSheetRunner runner = new CharacterSheetRunner(outputStreamFactory, characterSheetWriter);
    runner.runWriting(shell, editorPart, runnableContext);
    return null;
  }

  private String getSuggestedName(IEditorPart editorPart) {
    IEditorInput editorInput = editorPart.getEditorInput();
    IModelIdentifier identifier = (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
    ICharacterId characterId = identifier.getCharacterId();
    ICharacterDescription description = (ICharacterDescription) ModelCache.getInstance().getModel(
        new ModelIdentifier(characterId, ICharacterDescription.MODEL_ID));
    if (description == null || description.getName().isEmpty()) {
      return new CharacterTemplateProvider().getTemplate(characterId).getName();
    }
    return description.getName().getText();
  }
}