package net.sf.anathema.framework.persistence;

import java.io.IOException;

import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.exception.RepositoryException;

public interface IRepositoryItemPersister {

  public void save(IRepositoryWriteAccess writeAccess, IItem item) throws IOException, RepositoryException;

  public IItem load(IRepositoryReadAccess readAccess) throws PersistenceException, RepositoryException;

  public IItem createNew(IAnathemaWizardModelTemplate template) throws PersistenceException;
}