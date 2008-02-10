package net.sf.anathema.character.sheet.wizard;

import net.sf.anathema.character.report.pdf.ReportExporter;
import net.sf.anathema.character.sheet.pdf.CharacterSheetPdfWriter;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

public class SheetExportWizard extends Wizard implements IExportWizard {

  private IWorkbench exportWorkbench;

  @Override
  public boolean performFinish() {
    Shell shell = exportWorkbench.getDisplay().getActiveShell();
    IEditorPart editorPart = exportWorkbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    new ReportExporter().export(shell, editorPart, getContainer(), new CharacterSheetPdfWriter());
    return true;
  }

  @Override
  public void init(IWorkbench workbench, IStructuredSelection selection) {
    exportWorkbench = workbench;
    setWindowTitle("Character Sheet");
    addPage(new WizardPage("Tja") {
      @Override
      public void createControl(Composite parent) {
        Label control = new Label(parent, SWT.NONE);
        control.setText("Aha");
        setPageComplete(true);
        setControl(control);
      }
    });
  }
}