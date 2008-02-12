package net.sf.anathema.basics.pdfexport;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.importexport.ExportFileSelectionStatusFactory;
import net.sf.anathema.basics.importexport.FileSelectionModel;
import net.sf.anathema.basics.importexport.FileSelectionWizardPage;
import net.sf.anathema.basics.importexport.IFileSelectionModel;
import net.sf.anathema.basics.importexport.IFileSelectionPageMessages;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

public abstract class AbstractPdfExportWizard extends Wizard implements IExportWizard {

  private IWorkbench exportWorkbench;
  private IFileSelectionModel fileSelectionModel;
  private BooleanModel openModel;
  private IEditorPart editorPart;

  @Override
  public final boolean performFinish() {
    Shell shell = exportWorkbench.getDisplay().getActiveShell();
    IOutputStreamFactory outputStreamFactory = new ExportOutputStreamFactory(fileSelectionModel, openModel);
    createRunner(outputStreamFactory).runWriting(shell, editorPart, getContainer());
    return true;
  }

  protected abstract IReportRunner createRunner(IOutputStreamFactory outputStreamFactory);

  @Override
  public final void init(IWorkbench workbench, IStructuredSelection selection) {
    exportWorkbench = workbench;
    editorPart = exportWorkbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    fileSelectionModel = new FileSelectionModel(new ExportFileSelectionStatusFactory());
    openModel = new BooleanModel(true);
    PdfExportDialog dialog = new PdfExportDialog(null, getSuggestedName(editorPart));
    addPage(new FileSelectionWizardPage(fileSelectionModel, openModel, createMessage(), dialog));
  }

  protected abstract String getSuggestedName(IEditorPart editor);

  protected abstract IFileSelectionPageMessages createMessage();
}