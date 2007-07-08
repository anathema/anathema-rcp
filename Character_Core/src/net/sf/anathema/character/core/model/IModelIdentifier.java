package net.sf.anathema.character.core.model;

import org.eclipse.core.resources.IFolder;

public interface IModelIdentifier {

  public IFolder getFolder();

  public String getId();
}