package net.sf.anathema.basics.repository.input;

import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.itemtype.IItemType;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

public class UnusedItemTypeFileFactory extends AbstractUnusedFileFactory<IProject> {

  public UnusedItemTypeFileFactory(IItemType itemType) {
    super(RepositoryUtilities.getProject(itemType), itemType.getFileExtension());
  }

  @Override
  protected IFile createFile(String fileName) {
    return getContainer().getFile(fileName);
  }
}