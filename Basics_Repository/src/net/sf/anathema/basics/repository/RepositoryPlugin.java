package net.sf.anathema.basics.repository;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class RepositoryPlugin extends AbstractUIPlugin {

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    IExtension[] itemTypes = Platform.getExtensionRegistry()
        .getExtensionPoint("net.sf.anathema.repository.itemtypes")
        .getExtensions();
    for (IExtension extension : itemTypes) {
      for (IConfigurationElement configurationElement : extension.getConfigurationElements()) {
        String folderName = configurationElement.getAttribute("folder");
        initProject(folderName);
      }
    }
  }

  private void initProject(String projectName) {
    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    IProject project = root.getProject(projectName);
    if (!project.exists()) {
      try {
        project.create(null);
        project.open(null);
      }
      catch (CoreException e) {
        e.printStackTrace();
      }
    }
  }
}