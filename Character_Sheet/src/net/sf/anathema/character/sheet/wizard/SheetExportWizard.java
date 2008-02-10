package net.sf.anathema.character.sheet.wizard;


import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.importwizard.ExportFileSelectionStatusFactory;
import net.sf.anathema.basics.importwizard.FileSelectionModel;
import net.sf.anathema.basics.importwizard.FileSelectionWizardPage;
import net.sf.anathema.basics.importwizard.IFileSelectionModel;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;
import net.sf.anathema.character.report.pdf.CharacterReportRunner;
import net.sf.anathema.character.sheet.pdf.CharacterSheetPdfWriter;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

public class SheetExportWizard extends Wizard implements IExportWizard {

  private IWorkbench exportWorkbench;
  private IFileSelectionModel fileSelectionModel;
  private BooleanModel openModel;
  private IEditorPart editorPart;

  @Override
  public boolean performFinish() {
    Shell shell = exportWorkbench.getDisplay().getActiveShell();
    IOutputStreamFactory outputStreamFactory = new ExportOutputStreamFactory(fileSelectionModel, openModel);
    CharacterReportRunner runner = new CharacterReportRunner(outputStreamFactory, new CharacterSheetPdfWriter());
    runner.runWriting(shell, editorPart, getContainer());
    return true;
  }

  @Override
  public void init(IWorkbench workbench, IStructuredSelection selection) {
    exportWorkbench = workbench;
    editorPart = exportWorkbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    setWindowTitle("Character Sheet");
    fileSelectionModel = new FileSelectionModel(new ExportFileSelectionStatusFactory());
    openModel = new BooleanModel(true);
    PdfExportDialog dialog = new PdfExportDialog(null, getSuggestedName());
    addPage(new FileSelectionWizardPage(fileSelectionModel, openModel, new SheetExportMessages(), dialog));
  }

  private String getSuggestedName() {
    IEditorInput editorInput = editorPart.getEditorInput();
    IModelIdentifier identifier = (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
    ICharacterId characterId = identifier.getCharacterId();
    IContainer container = (IContainer) characterId.getAdapter(IContainer.class);
    return new CharacterDisplayNameProvider(container).getDisplayName();
  }
}