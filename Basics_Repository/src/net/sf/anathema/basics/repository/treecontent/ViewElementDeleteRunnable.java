package net.sf.anathema.basics.repository.treecontent;

import java.lang.reflect.InvocationTargetException;

import net.sf.anathema.basics.repository.treecontent.deletion.IPageDelible;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IWorkbenchPage;

public final class ViewElementDeleteRunnable implements IRunnableWithProgress {
  private final IPageDelible element;
  private final IWorkbenchPage page;

  public ViewElementDeleteRunnable(IWorkbenchPage page, IPageDelible element) {
    this.page = page;
    this.element = element;
  }

  @Override
  public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
    try {
      element.delete(page, monitor);
    }
    catch (Exception e) {
      throw new InvocationTargetException(e);
    }
  }
}