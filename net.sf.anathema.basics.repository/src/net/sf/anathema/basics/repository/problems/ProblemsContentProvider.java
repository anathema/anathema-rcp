package net.sf.anathema.basics.repository.problems;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.repository.RepositoryPlugin;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.viewers.Viewer;

public class ProblemsContentProvider extends AbstractFlatTreeContentProvider {

  private static final String POINT_ID = "problemproviders"; //$NON-NLS-1$
  private final List<IProblemProvider> problemProviders;

  public ProblemsContentProvider() {
    EclipseExtensionPoint extensionPoint = new EclipseExtensionPoint(RepositoryPlugin.ID, POINT_ID);
    this.problemProviders = new ClassConveyerBelt<IProblemProvider>(extensionPoint, IProblemProvider.class).getAllObjects();
  }

  @Override
  public void dispose() {
    // nothing to do
  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // nothing to do
  }

  @Override
  public IProblem[] getElements(Object inputElement) {
    List<IProblem> problems = new ArrayList<IProblem>();
    for (IProblemProvider provider : problemProviders) {
      problems.addAll(provider.findProblems((IContainer) inputElement));
    }
    return problems.toArray(new IProblem[problems.size()]);
  }
}