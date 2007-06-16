package net.sf.anathema.character.core.repository;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IExecutableExtension;

public interface ICharacterModelViewElementFactory extends IExecutableExtension {

  public IViewElement create(IViewElement parent, IFolder characterFolder);
}