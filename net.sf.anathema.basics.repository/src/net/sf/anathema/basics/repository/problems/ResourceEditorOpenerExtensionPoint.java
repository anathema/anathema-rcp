package net.sf.anathema.basics.repository.problems;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.ui.IResourceEditorOpener;
import net.sf.anathema.basics.repository.RepositoryPlugin;

import org.eclipse.core.resources.IResource;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class ResourceEditorOpenerExtensionPoint {

  public static final class OpenerIdPredicate implements IPredicate<IExtensionElement> {
    private final String openerId;

    public OpenerIdPredicate(String openerId) {
      this.openerId = openerId;
    }

    @Override
    public boolean evaluate(IExtensionElement element) {
      return openerId.equals(element.getAttribute(ATTRIB_OPENER_ID));
    }
  }

  private static final String ATTRIB_OPENER_ID = "openerId"; //$NON-NLS-1$

  public void open(IWorkbenchPage page, String openerId, IResource resource) throws PartInitException {
    if (openerId == null) {
      return;
    }
    IResourceEditorOpener opener = getOpener(openerId);
    opener.openEditor(page, resource);
  }

  private IResourceEditorOpener getOpener(final String openerId) {
    EclipseExtensionPoint extensionPoint = new EclipseExtensionPoint(RepositoryPlugin.ID, "sourceopener"); //$NON-NLS-1$
    OpenerIdPredicate predicate = new OpenerIdPredicate(openerId);
    IResourceEditorOpener opener = new ClassConveyerBelt<IResourceEditorOpener>(
        extensionPoint,
        IResourceEditorOpener.class,
        predicate).getFirstObject();
    if (opener == ClassConveyerBelt.NO_OBJECT_FOUND) {
      return new NullOpener();
    }
    return opener;
  }
}