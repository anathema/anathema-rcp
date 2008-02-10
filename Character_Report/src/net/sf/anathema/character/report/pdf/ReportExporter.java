package net.sf.anathema.character.report.pdf;

import net.sf.anathema.basics.swt.file.PdfFileOutputStreamFactory;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

public class ReportExporter {

  public void export(
      Shell shell,
      IEditorPart editorPart,
      IRunnableContext runnableContext,
      ICharacterReportWriter reportWriter) {
    PdfFileOutputStreamFactory outputStreamFactory = new PdfFileOutputStreamFactory(getSuggestedName(editorPart));
    CharacterReportRunner runner = new CharacterReportRunner(outputStreamFactory, reportWriter);
    runner.runWriting(shell, editorPart, runnableContext);
  }

  private String getSuggestedName(IEditorPart editorPart) {
    IEditorInput editorInput = editorPart.getEditorInput();
    IModelIdentifier identifier = (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
    ICharacterId characterId = identifier.getCharacterId();
    IContainer container = (IContainer) characterId.getAdapter(IContainer.class);
    return new CharacterDisplayNameProvider(container).getDisplayName();
  }
}
