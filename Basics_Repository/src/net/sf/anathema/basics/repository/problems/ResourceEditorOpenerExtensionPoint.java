package net.sf.anathema.basics.repository.problems;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.ui.IResourceEditorOpener;
import net.sf.anathema.basics.repository.RepositoryPlugin;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class ResourceEditorOpenerExtensionPoint {

  private static final String ATTRIB_CLASS = "class"; //$NON-NLS-1$
  private static final String ATTRIB_OPENER_ID = "openerId"; //$NON-NLS-1$

  public void open(IWorkbenchPage page, String openerId, IResource resource) throws PartInitException {
    if (openerId == null) {
      return;
    }
    IResourceEditorOpener opener = null;
    try {
      opener = getOpener(openerId);
    }
    catch (ExtensionException e) {
      RepositoryPlugin.getDefaultInstance().log(
          IStatus.ERROR,
          "Error opening resource " + resource + " with openerId " + openerId,
          e);
    }
    if (opener == null) {
      return;
    }
    opener.openEditor(page, resource);
  }

  private IResourceEditorOpener getOpener(String openerId) throws ExtensionException {
    for (IPluginExtension extension : new EclipseExtensionPoint(RepositoryPlugin.ID, "sourceopener").getExtensions()) { //$NON-NLS-1$
      for (IExtensionElement element : extension.getElements()) {
        if (openerId.equals(element.getAttribute(ATTRIB_OPENER_ID))) {
          return element.getAttributeAsObject(ATTRIB_CLASS, IResourceEditorOpener.class);
        }
      }
    }
    return null;
  }
}