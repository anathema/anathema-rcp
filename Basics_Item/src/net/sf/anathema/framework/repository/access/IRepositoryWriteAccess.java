package net.sf.anathema.framework.repository.access;

import java.io.OutputStream;

import net.sf.anathema.lib.exception.RepositoryException;

public interface IRepositoryWriteAccess {

  public OutputStream createMainOutputStream() throws RepositoryException;

  public OutputStream createSubOutputStream(String streamID) throws RepositoryException;
}