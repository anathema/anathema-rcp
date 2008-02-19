package net.sf.anathema.character.report.wizard;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.pdfexport.AbstractPdfExportWizard;
import net.sf.anathema.basics.pdfexport.IReportRunner;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;
import net.sf.anathema.basics.pdfexport.writer.IReportWriter;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.create.CharacterRepositoryUtilities;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;
import net.sf.anathema.character.report.internal.wizard.CharacterReportRunner;

import org.eclipse.core.resources.IContainer;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IExportWizard;

public abstract class AbstractCharacterExportWizard extends AbstractPdfExportWizard<ICharacter> implements
    IExportWizard {

  @Override
  protected IReportRunner<ICharacter> createRunner(IOutputStreamFactory outputStreamFactory) {
    return new CharacterReportRunner(outputStreamFactory, createCharacterPdfWriter());
  }
  
  @Override
  protected boolean supportsExportItems() {
    return true;
  }
  
  @Override
  protected List<IExportItem<ICharacter>> getExportItems() {
    List<IExportItem<ICharacter>> exportItems = new ArrayList<IExportItem<ICharacter>>();
    for (ICharacterId characterId : CharacterRepositoryUtilities.getAllCharacterIds()) {
      exportItems.add(new CharacterExportItem(characterId));
    }
    return exportItems;
  }

  @Override
  protected String getSuggestedName(IEditorPart editorPart) {
    IEditorInput editorInput = editorPart.getEditorInput();
    IModelIdentifier identifier = (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
    ICharacterId characterId = identifier.getCharacterId();
    IContainer container = (IContainer) characterId.getAdapter(IContainer.class);
    return new CharacterDisplayNameProvider(container).getDisplayName();
  }

  protected abstract IReportWriter<ICharacter> createCharacterPdfWriter();
}