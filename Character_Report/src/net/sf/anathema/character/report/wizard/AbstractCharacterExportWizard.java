package net.sf.anathema.character.report.wizard;

import net.sf.anathema.basics.pdfexport.AbstractPdfExportWizard;
import net.sf.anathema.basics.pdfexport.IReportRunner;
import net.sf.anathema.basics.pdfexport.writer.IReportWriter;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;
import net.sf.anathema.character.report.internal.wizard.CharacterReportRunner;

import org.eclipse.core.resources.IContainer;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IExportWizard;

public abstract class AbstractCharacterExportWizard extends AbstractPdfExportWizard implements IExportWizard {

  @Override
  protected IReportRunner createRunner(IOutputStreamFactory outputStreamFactory) {
    return new CharacterReportRunner(outputStreamFactory, createCharacterPdfWriter());
  }

  @Override
  protected String getSuggestedName(IEditorPart editorPart) {
    IEditorInput editorInput = editorPart.getEditorInput();
    IModelIdentifier identifier = (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
    ICharacterId characterId = identifier.getCharacterId();
    IContainer container = (IContainer) characterId.getAdapter(IContainer.class);
    return new CharacterDisplayNameProvider(container).getDisplayName();
  }

  protected abstract IReportWriter createCharacterPdfWriter();
}