package net.sf.anathema.basics.repository.treecontent.itemtype;

import java.util.List;

import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.itemtype.IItemType;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public abstract class AbstractFolderBasedViewElementFactory implements IItemTypeViewElementFactory {

  private IItemType itemType;

  protected final List<IFolder> getMembers() {
    return RepositoryUtilities.getItemFolders(itemType);
  }

  protected final IItemType getItemType() {
    return itemType;
  }

  @Override
  public final void setItemType(IItemType itemType) {
    this.itemType = itemType;
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}