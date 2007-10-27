package net.sf.anathema.basics.eclipse.resource;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.Path;

public class FileUtils {

  public static IFolder createUnusedFolder(IContainer container, String suggestedFolderName) {
    int count = 0;
    IFolder folder = container.getFolder(new Path(suggestedFolderName));
    while (folder.exists()) {
      folder = container.getFolder(new Path(suggestedFolderName + ++count));
    }
    return folder;
  }
}