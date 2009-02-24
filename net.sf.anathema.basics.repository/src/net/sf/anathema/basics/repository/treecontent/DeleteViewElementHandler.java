package net.sf.anathema.basics.repository.treecontent;

import net.sf.anathema.basics.repository.treecontent.deletion.IPageDelible;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.handlers.HandlerUtil;

public class DeleteViewElementHandler extends AbstractHandler {
  private final ViewElementDeleter deleter = new ViewElementDeleter();

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    StructuredSelection selection = (StructuredSelection) HandlerUtil.getCurrentSelection(event);
    deleter.setPage(HandlerUtil.getActiveSite(event).getPage());
    if (selection.size() == 1) {
      deleteSingleElement(event, selection);
    }
    else {
      deleteMultipleElements(event, selection);
    }
    return null;
  }

  private void deleteMultipleElements(ExecutionEvent event, StructuredSelection selection) {
    boolean confirmed = confirmDeletion(event, selection.size() + " elements");
    if (confirmed) {
      for (Object element : selection.toArray()) {
        deleteElement((IViewElement) element);
      }
    }
  }

  private void deleteSingleElement(ExecutionEvent event, StructuredSelection selection) {
    IViewElement element = (IViewElement) selection.getFirstElement();
    boolean confirmed = confirmDeletion(event, '"' + element.getDisplayName() + '"');
    if (confirmed) {
      deleteElement(element);
    }
  }

  private void deleteElement(IViewElement element) {
    deleter.delete((IPageDelible) element.getAdapter(IPageDelible.class));
  }

  private boolean confirmDeletion(ExecutionEvent event, String completion) {
    return MessageDialog.openQuestion(
        HandlerUtil.getActiveShell(event),
        Messages.DeleteViewElementActionDelegate_Confirm_Dialog_Title,
        NLS.bind(Messages.DeleteViewElementActionDelegate_Confirm_Dialog_Message, completion));
  }
}