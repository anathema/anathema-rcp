package net.sf.anathema.basics.repository.persistence;

import net.sf.anathema.framework.item.IBasicRepositoryIdData;
import net.sf.anathema.lib.util.IIdentificate;

public interface IItemRepositoryLocation extends IBasicRepositoryIdData, IIdentificate {

  public void setId(String id);
}