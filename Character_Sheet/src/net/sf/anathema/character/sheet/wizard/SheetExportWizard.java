package net.sf.anathema.character.sheet.wizard;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.importwizard.FileSelectionModel;
import net.sf.anathema.basics.importwizard.FileSelectionStatusFactory;
import net.sf.anathema.basics.importwizard.FileSelectionWizardPage;
import net.sf.anathema.basics.importwizard.IFileSelectionModel;
import net.sf.anathema.basics.importwizard.IFileSelectionPageMessages;
import net.sf.anathema.basics.swt.file.BrowserControl;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;
import net.sf.anathema.basics.swt.file.IStreamResult;
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

  private final class SheetExportMessages implements IFileSelectionPageMessages {
    @Override
    public String getPageTitle() {
      return "Export Character Sheet";
    }

    @Override
    public String getPageName() {
      return "File Selection";
    }

    @Override
    public String getOpenLabel() {
      return "Open file after export";
    }

    @Override
    public String getDescription() {
      return "Select pdf file for export";
    }
  }

  private IWorkbench exportWorkbench;
  private IFileSelectionModel fileSelectionModel;
  private BooleanModel openModel;
  private IEditorPart editorPart;

  @Override
  public boolean performFinish() {
    Shell shell = exportWorkbench.getDisplay().getActiveShell();
    IOutputStreamFactory outputStreamFactory = new IOutputStreamFactory() {
      @Override
      public IStreamResult create(Shell shell) throws FileNotFoundException {
        return new IStreamResult() {

          @Override
          public void openResult() {
            if (openModel.getValue()) {
              BrowserControl.displayUrl(fileSelectionModel.getFile().toURI());
            }
          }

          @Override
          public OutputStream createStream() throws IOException {
            if (fileSelectionModel.isComplete()) {
              return new FileOutputStream(fileSelectionModel.getFile());
            }
            return null;
          }
        };
      }
    };
    CharacterReportRunner runner = new CharacterReportRunner(outputStreamFactory, new CharacterSheetPdfWriter());
    runner.runWriting(shell, editorPart, getContainer());
    return true;
  }

  @Override
  public void init(IWorkbench workbench, IStructuredSelection selection) {
    exportWorkbench = workbench;
    editorPart = exportWorkbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    setWindowTitle("Character Sheet");
    fileSelectionModel = new FileSelectionModel(new FileSelectionStatusFactory());
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