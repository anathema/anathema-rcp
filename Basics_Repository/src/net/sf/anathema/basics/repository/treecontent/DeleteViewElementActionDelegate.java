package net.sf.anathema.basics.repository.treecontent;

import net.sf.anathema.basics.repository.treecontent.deletion.IPageDelible;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;

public class DeleteViewElementActionDelegate implements IObjectActionDelegate {

  private IViewElement element;
  private final ViewElementDeleter deleter = new ViewElementDeleter();
  private Shell shell;

  @Override
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    IWorkbenchPartSite site = targetPart.getSite();
    this.shell = site.getShell();
    deleter.setPage(site.getPage());
  }

  @Override
  public void run(IAction action) {
    if (!action.isEnabled() || element == null) {
      return;
    }
    boolean confirmed = MessageDialog.openQuestion(
        shell,
        Messages.DeleteViewElementActionDelegate_Confirm_Dialog_Title,
        NLS.bind(Messages.DeleteViewElementActionDelegate_Confirm_Dialog_Message, element.getDisplayName()));
    if (!confirmed) {
      return;
    }
    deleter.delete((IPageDelible) element.getAdapter(IPageDelible.class));
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    IStructuredSelection structuredSelection = (IStructuredSelection) selection;
    if (structuredSelection != null && structuredSelection.getFirstElement() instanceof IViewElement) {
      element = (IViewElement) structuredSelection.getFirstElement();
      action.setEnabled(element.getAdapter(IPageDelible.class) != null);
    }
  }
}