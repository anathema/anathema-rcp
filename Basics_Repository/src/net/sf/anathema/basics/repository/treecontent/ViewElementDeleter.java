package net.sf.anathema.basics.repository.treecontent;

import net.sf.anathema.basics.repository.RepositoryPlugin;
import net.sf.anathema.basics.repository.treecontent.itemtype.IPageDelible;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.IWorkbenchPage;

public class ViewElementDeleter {

  private IWorkbenchPage page;

  public void delete(IPageDelible element) {
    try {
      element.delete(page);
    }
    catch (Exception e) {
      RepositoryPlugin.getDefaultInstance().log(IStatus.ERROR, Messages.ViewElementDeleter_Error, e);
    }
  }

  public void setPage(IWorkbenchPage page) {
    this.page = page;
  }
}