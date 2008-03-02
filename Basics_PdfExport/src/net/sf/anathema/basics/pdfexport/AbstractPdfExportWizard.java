package net.sf.anathema.basics.pdfexport;

import java.util.List;

import net.disy.commons.core.model.BooleanModel;
import net.disy.commons.core.model.ObjectModel;
import net.sf.anathema.basics.importexport.ExportFileSelectionStatusFactory;
import net.sf.anathema.basics.importexport.FileSelectionModel;
import net.sf.anathema.basics.importexport.FileSelectionWizardPage;
import net.sf.anathema.basics.importexport.IFileSelectionModel;
import net.sf.anathema.basics.importexport.IFileSelectionPageMessages;
import net.sf.anathema.basics.pdfexport.item.ExportItemDialogPage;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;
import net.sf.anathema.basics.swt.file.FileChoosingPreference;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

public abstract class AbstractPdfExportWizard<I> extends Wizard implements IExportWizard {

  private IWorkbench exportWorkbench;
  private IFileSelectionModel fileSelectionModel;
  private BooleanModel openModel;
  private ObjectModel<IExportItem<I>> selectedItem;

  @Override
  public final boolean performFinish() {
    Shell shell = exportWorkbench.getDisplay().getActiveShell();
    if (fileSelectionModel.getFile().exists()) {
      MessageBox box = new MessageBox(shell, SWT.YES | SWT.NO | SWT.ICON_WARNING | SWT.APPLICATION_MODAL);
      box.setText(Messages.AbstractPdfExportWizard_FileExistsBox_Title);
      box.setMessage(Messages.AbstractPdfExportWizard_FileExistsBox_Question);
      if (SWT.NO == box.open()) {
        return false;
      }
    }
    IOutputStreamFactory outputStreamFactory = new ExportOutputStreamFactory(fileSelectionModel, openModel);
    createRunner(outputStreamFactory).runWriting(shell, selectedItem.getValue(), getContainer());
    return true;
  }

  protected abstract IReportRunner<I> createRunner(IOutputStreamFactory outputStreamFactory);

  @Override
  public final void init(IWorkbench workbench, IStructuredSelection selection) {
    exportWorkbench = workbench;
    fileSelectionModel = new FileSelectionModel(new ExportFileSelectionStatusFactory());
    openModel = new BooleanModel(true);
    List<IExportItem<I>> exportItems = getExportItems();
    selectedItem = new ObjectModel<IExportItem<I>>();
    final ExportPrintNameProvider<I> fileNameProvider = new ExportPrintNameProvider<I>(selectedItem);
    selectedItem.addChangeListener(new FileSelectionModelUpdater<I>(
        new FileChoosingPreference(),
        fileSelectionModel,
        fileNameProvider));
    IEditorPart activeEditor = exportWorkbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    if (activeEditor != null) {
      selectedItem.setValue(getSelectedItem(exportItems, activeEditor.getEditorInput()));
    }
    PdfExportDialog dialog = new PdfExportDialog(fileNameProvider);
    addPage(new ExportItemDialogPage<I>(exportItems, selectedItem));
    addPage(new FileSelectionWizardPage(fileSelectionModel, openModel, createMessage(), dialog));
  }

  private IExportItem<I> getSelectedItem(List<IExportItem<I>> exportItems, IEditorInput editorInput) {
    IResource resource = (IResource) editorInput.getAdapter(IResource.class);
    if (resource == null) {
      return null;
    }
    for (IExportItem<I> item : exportItems) {
      if (item.isFor(resource)) {
        return item;
      }
    }
    return null;
  }

  protected abstract List<IExportItem<I>> getExportItems();

  protected abstract IFileSelectionPageMessages createMessage();
}