package net.sf.anathema.basics.repository.treecontent;

import java.lang.reflect.InvocationTargetException;

import net.sf.anathema.basics.repository.RepositoryPlugin;
import net.sf.anathema.basics.repository.treecontent.deletion.IPageDelible;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.IWorkbenchPage;

public class ViewElementDeleter {

  private IWorkbenchPage page;

  public void delete(final IPageDelible element) {
    try {
      page.getWorkbenchWindow().run(false, false, new ViewElementDeleteRunnable(page, element));
    }
    catch (InvocationTargetException e) {
      RepositoryPlugin.getDefaultInstance().log(
          IStatus.ERROR,
          Messages.ViewElementDeleter_Error,
          e.getTargetException());
    }
    catch (InterruptedException e) {
      RepositoryPlugin.getDefaultInstance().log(IStatus.ERROR, Messages.ViewElementDeleter_DeleteInterruptedError, e);
    }
  }

  public void setPage(IWorkbenchPage page) {
    this.page = page;
  }
}