package net.sf.anathema.basics.repository.persistence;

import net.sf.anathema.framework.item.data.IItemData;
import net.sf.anathema.framework.item.role.AbstractItemRole;

public class RepositoryLocationItem<D extends IItemData> extends AbstractItemRole<D> {
  private final RepositoryLocation repositoryLocation;

  public RepositoryLocationItem() {
    this.repositoryLocation = new RepositoryLocation();
  }

  public IItemRepositoryLocation getRepositoryLocation() {
    return repositoryLocation;
  }
}