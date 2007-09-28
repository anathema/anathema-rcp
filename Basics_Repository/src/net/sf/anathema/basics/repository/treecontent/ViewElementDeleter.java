package net.sf.anathema.basics.repository.treecontent;

import java.lang.reflect.InvocationTargetException;

import net.sf.anathema.basics.repository.RepositoryPlugin;
import net.sf.anathema.basics.repository.treecontent.deletion.IPageDelible;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IWorkbenchPage;

public class ViewElementDeleter {

  private IWorkbenchPage page;

  public void delete(final IPageDelible element) {
    try {
      page.getWorkbenchWindow().run(true, false, new IRunnableWithProgress() {
        @Override
        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
          try {
            element.delete(page, monitor);
          }
          catch (Exception e) {
            throw new InvocationTargetException(e);
          }
        }
      });
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