package net.sf.anathema.notes;

import net.sf.anathema.basics.repository.input.NewItemEditorInput;
import net.sf.anathema.basics.repository.input.ProxyItemEditorInput;
import net.sf.anathema.basics.repository.itemtype.IItemType;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class NewNoteActionDelegate implements IObjectActionDelegate {

  private IWorkbenchPart targetPart;

  @Override
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    this.targetPart = targetPart;
  }

  @Override
  public void run(IAction action) {
    IWorkbenchPage page = targetPart.getSite().getPage();
    IEditorDescriptor defaultEditor = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor("Hasä.not");
    IItemType itemType = NotesRepositoryUtilities.getNotesItemType();
    IEditorInput input = new ProxyItemEditorInput(itemType.getUntitledName(), new NewItemEditorInput(itemType));
    try {
      page.openEditor(input, defaultEditor.getId());
    }
    catch (PartInitException e) {
      // TODO Fehlerhandling
      e.printStackTrace();
    }
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    // nothing to do
  }
}