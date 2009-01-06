package net.sf.anathema.charms.textencoder;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.trait.resources.INameCollection;

public class EmptyNameCollection extends AbstractExecutableExtension implements INameCollection {

  @Override
  public String getName(String id) {
    if ("".equals(id)) {
      return " ";
    }
    return id;
  }
}