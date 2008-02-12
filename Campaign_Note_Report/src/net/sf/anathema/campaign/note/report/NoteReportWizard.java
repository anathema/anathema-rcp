package net.sf.anathema.campaign.note.report;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.importexport.ExportFileSelectionStatusFactory;
import net.sf.anathema.basics.importexport.FileSelectionModel;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

public class NoteReportWizard extends Wizard implements IExportWizard {

  private IWorkbench exportWorkbench;
  private IEditorPart editorPart;
  private Object fileSelectionModel;
  private BooleanModel openModel;

  @Override
  public boolean performFinish() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void init(IWorkbench workbench, IStructuredSelection selection) {
    exportWorkbench = workbench;
    editorPart = exportWorkbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    fileSelectionModel = new FileSelectionModel(new ExportFileSelectionStatusFactory());
    openModel = new BooleanModel(true);
    // PdfExportDialog dialog = new PdfExportDialog(null, getSuggestedName());
    // addPage(new FileSelectionWizardPage(fileSelectionModel, openModel, createMessage(), dialog));
  }
}