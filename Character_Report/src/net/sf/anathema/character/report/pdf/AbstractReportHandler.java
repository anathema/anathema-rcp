package net.sf.anathema.character.report.pdf;

import net.sf.anathema.basics.swt.file.PdfFileOutputStreamFactory;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;
import net.sf.anathema.character.report.internal.pdf.CharacterReportRunner;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

public abstract class AbstractReportHandler extends AbstractHandler {

  protected abstract ICharacterReportWriter createReportWriter();

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    Shell shell = HandlerUtil.getActiveShell(event);
    IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
    PdfFileOutputStreamFactory outputStreamFactory = new PdfFileOutputStreamFactory(getSuggestedName(editorPart));
    ICharacterReportWriter reportWriter = createReportWriter();
    IRunnableContext runnableContext = HandlerUtil.getActiveWorkbenchWindow(event);
    CharacterReportRunner runner = new CharacterReportRunner(outputStreamFactory, reportWriter);
    runner.runWriting(shell, editorPart, runnableContext);
    return null;
  }

  private String getSuggestedName(IEditorPart editorPart) {
    IEditorInput editorInput = editorPart.getEditorInput();
    IModelIdentifier identifier = (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
    ICharacterId characterId = identifier.getCharacterId();
    IContainer container = (IContainer) characterId.getAdapter(IContainer.class);
    return new CharacterDisplayNameProvider(container).getDisplayName();
  }

}