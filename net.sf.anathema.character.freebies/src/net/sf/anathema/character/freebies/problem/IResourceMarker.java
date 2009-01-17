package net.sf.anathema.character.freebies.problem;

import org.eclipse.core.runtime.CoreException;

public interface IResourceMarker {

  public boolean exists() throws CoreException;

  public void create() throws CoreException;

  public void delete() throws CoreException;
}