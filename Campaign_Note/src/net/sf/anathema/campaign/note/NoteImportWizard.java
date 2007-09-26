package net.sf.anathema.campaign.note;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

public class NoteImportWizard extends Wizard implements IImportWizard {

  private final NoteFileSelectionWizardPage wizardPageExtension = new NoteFileSelectionWizardPage();

  @Override
  public boolean canFinish() {
    return wizardPageExtension.isPageComplete();
  }

  @Override
  public boolean performFinish() {
    // TODO: Actually import the file.
    // TODO: Open when finished
    return true;
  }

  @Override
  public void init(IWorkbench workbench, IStructuredSelection selection) {
    setWindowTitle("Anathema Classic Note");
    addPage(wizardPageExtension);
  }
}