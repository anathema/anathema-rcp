package net.sf.anathema.basics.repository.problems;

import java.util.Iterator;

import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.repository.RepositoryPlugin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.ui.PlatformUI;

public final class ProblemOpenListener implements IOpenListener {

  @Override
  public void open(OpenEvent event) {
    try {
      IStructuredSelection selection = (IStructuredSelection) event.getSelection();
      for (Iterator<IProblem> allObjects = getIterator(selection); allObjects.hasNext();) {
        IProblem problem = allObjects.next();
        problem.showSource(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage());
      }
    }
    catch (CoreException e) {
      new Logger(RepositoryPlugin.ID).error(Messages.ProblemOpenListener_ErrorMessage, e);
    }
  }

  @SuppressWarnings("unchecked")
  private Iterator<IProblem> getIterator(IStructuredSelection selection) {
    return selection.iterator();
  }
}