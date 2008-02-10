package net.sf.anathema.character.report.wizard;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.importexport.ExportFileSelectionStatusFactory;
import net.sf.anathema.basics.importexport.FileSelectionModel;
import net.sf.anathema.basics.importexport.FileSelectionWizardPage;
import net.sf.anathema.basics.importexport.IFileSelectionModel;
import net.sf.anathema.basics.importexport.IFileSelectionPageMessages;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;
import net.sf.anathema.character.report.internal.wizard.CharacterReportRunner;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

public abstract class AbstractCharacterExportWizard extends Wizard implements IExportWizard {

  private IWorkbench exportWorkbench;
  private IFileSelectionModel fileSelectionModel;
  private BooleanModel openModel;
  private IEditorPart editorPart;

  @Override
  public final boolean performFinish() {
    Shell shell = exportWorkbench.getDisplay().getActiveShell();
    IOutputStreamFactory outputStreamFactory = new ExportOutputStreamFactory(fileSelectionModel, openModel);
    CharacterReportRunner runner = new CharacterReportRunner(outputStreamFactory, createCharacterPdfWriter());
    runner.runWriting(shell, editorPart, getContainer());
    return true;
  }

  @Override
  public final void init(IWorkbench workbench, IStructuredSelection selection) {
    exportWorkbench = workbench;
    editorPart = exportWorkbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    fileSelectionModel = new FileSelectionModel(new ExportFileSelectionStatusFactory());
    openModel = new BooleanModel(true);
    PdfExportDialog dialog = new PdfExportDialog(null, getSuggestedName());
    addPage(new FileSelectionWizardPage(fileSelectionModel, openModel, createMessage(), dialog));
  }

  private String getSuggestedName() {
    IEditorInput editorInput = editorPart.getEditorInput();
    IModelIdentifier identifier = (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
    ICharacterId characterId = identifier.getCharacterId();
    IContainer container = (IContainer) characterId.getAdapter(IContainer.class);
    return new CharacterDisplayNameProvider(container).getDisplayName();
  }

  protected abstract IFileSelectionPageMessages createMessage();

  protected abstract ICharacterReportWriter createCharacterPdfWriter();
}