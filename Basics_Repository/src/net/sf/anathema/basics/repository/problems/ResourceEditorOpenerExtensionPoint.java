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

  private static final String ATTRIB_OPENER_ID = "openerId"; //$NON-NLS-1$

  public void open(IWorkbenchPage page, String openerId, IResource resource) throws PartInitException {
    if (openerId == null) {
      return;
    }
    IResourceEditorOpener opener = null;
    opener = getOpener(openerId);
    if (opener == null) {
      return;
    }
    opener.openEditor(page, resource);
  }

  private IResourceEditorOpener getOpener(final String openerId) {
    EclipseExtensionPoint extensionPoint = new EclipseExtensionPoint(RepositoryPlugin.ID, "sourceopener"); //$NON-NLS-1$
    return new ClassConveyerBelt<IResourceEditorOpener>(extensionPoint, IResourceEditorOpener.class).getFirstObject(new IPredicate<IExtensionElement>() {
      @Override
      public boolean evaluate(IExtensionElement element) {
        return openerId.equals(element.getAttribute(ATTRIB_OPENER_ID));
      }
    });
  }
}