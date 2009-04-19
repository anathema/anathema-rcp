package net.sf.anathema.character.trait.resources;

import org.eclipse.core.runtime.IExecutableExtension;

public interface INameCollection extends IExecutableExtension {

  public String getName(String id);

  public boolean knowsNameFor(String id);
}