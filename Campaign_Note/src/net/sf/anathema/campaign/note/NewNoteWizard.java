package net.sf.anathema.campaign.note;

import net.sf.anathema.basics.repository.creation.EmptyNewWizard;
import net.sf.anathema.basics.repository.input.NewItemEditorInput;
import net.sf.anathema.basics.repository.input.ProxyItemEditorInput;
import net.sf.anathema.basics.repository.itemtype.IItemType;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class NewNoteWizard extends EmptyNewWizard {

  private static final String PLUGIN_ID = "net.sf.anathema.campaign.note"; //$NON-NLS-1$
  private static final String NOTES_EDITOR_ID = "net.sf.anathema.campaign.note.noteeditor"; //$NON-NLS-1$

  @Override
  public boolean performFinish() {
    openEditorForNewNote(getWorkbenchPage());
    return true;
  }

  public static void openEditorForNewNote(IWorkbenchPage page) {
    IItemType itemType = NotesRepositoryUtilities.getNotesItemType();
    IEditorInput input = new ProxyItemEditorInput(itemType.getUntitledName(), new NewItemEditorInput(itemType));
    try {
      page.openEditor(input, NOTES_EDITOR_ID);
    }
    catch (PartInitException e) {
      Platform.getLog(Platform.getBundle(PLUGIN_ID)).log(
          new Status(IStatus.ERROR, PLUGIN_ID, Messages.NewNoteActionDelegate_NoteCreationError, e));
    }
  }
}