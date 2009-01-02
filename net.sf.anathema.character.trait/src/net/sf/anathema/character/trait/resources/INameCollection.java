package net.sf.anathema.character.trait.resources;

import org.eclipse.core.runtime.IExecutableExtension;

public interface INameCollection extends IExecutableExtension {

  String getName(String id);
}