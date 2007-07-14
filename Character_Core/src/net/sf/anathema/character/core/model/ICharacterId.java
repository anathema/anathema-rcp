package net.sf.anathema.character.core.model;

import org.eclipse.core.resources.IFile;

public interface ICharacterId {

  IFile getContents(String fileName);
}