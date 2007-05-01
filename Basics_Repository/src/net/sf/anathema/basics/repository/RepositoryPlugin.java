package net.sf.anathema.basics.repository;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class RepositoryPlugin extends AbstractUIPlugin {

  private static final String ATTRIB_FOLDER = "folder"; //$NON-NLS-1$
  private static final String REPOSITORY_ITEMTYPES = "net.sf.anathema.repository.itemtypes"; //$NON-NLS-1$

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    String extensionName = REPOSITORY_ITEMTYPES;
    for (IExtension extension : Platform.getExtensionRegistry().getExtensionPoint(extensionName).getExtensions()) {
      for (IConfigurationElement configurationElement : extension.getConfigurationElements()) {
        String folderName = configurationElement.getAttribute(ATTRIB_FOLDER);
        initProject(folderName);
      }
    }
  }

  private void initProject(String projectName) throws Exception {
    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    IProject project = root.getProject(projectName);
    if (project.exists()) {
      return;
    }
    project.create(null);
    project.open(null);
  }
}