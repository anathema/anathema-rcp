package net.sf.anathema.basics.repository.problems;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.repository.RepositoryPlugin;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.osgi.util.NLS;

public class ProblemsContentProvider extends AbstractFlatTreeContentProvider {

  private static final String ATTRIB_CLASS = "class"; //$NON-NLS-1$
  private static final String POINT_ID = "problemproviders"; //$NON-NLS-1$
  private final List<IProblemProvider> problemProviders = new ArrayList<IProblemProvider>();

  public ProblemsContentProvider() {
    for (IPluginExtension extension : new EclipseExtensionPoint(RepositoryPlugin.ID, POINT_ID).getExtensions()) {
      for (IExtensionElement element : extension.getElements()) {
        try {
          problemProviders.add(element.getAttributeAsObject(ATTRIB_CLASS, IProblemProvider.class));
        }
        catch (ExtensionException e) {
          String message = NLS.bind("Error loading problem provider {0}.", element.getAttribute(ATTRIB_CLASS));
          new Logger(RepositoryPlugin.ID).error(message, e);
        }
      }
    }
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
      problems.addAll(provider.findProblems((IWorkspaceRoot) inputElement));
    }
    return problems.toArray(new IProblem[problems.size()]);
  }
}