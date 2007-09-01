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
    IViewElement element = (IViewElement) ((StructuredSelection) HandlerUtil.getCurrentSelection(event)).getFirstElement();
    deleter.setPage(HandlerUtil.getActiveSite(event).getPage());
    boolean confirmed = MessageDialog.openQuestion(
        HandlerUtil.getActiveShell(event),
        Messages.DeleteViewElementActionDelegate_Confirm_Dialog_Title,
        NLS.bind(Messages.DeleteViewElementActionDelegate_Confirm_Dialog_Message, element.getDisplayName()));
    if (confirmed) {
      deleter.delete((IPageDelible) element.getAdapter(IPageDelible.class));
    }
    return null;
  }
}