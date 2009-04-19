package net.sf.anathema.charms.textencoder;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.trait.resources.INameCollection;

@SuppressWarnings("nls")
public class EmptyNameCollection extends UnconfiguredExecutableExtension implements INameCollection {

  @Override
  public String getName(String id) {
    if (knowsNameFor(id)) {
      return " ";
    }
    return id;
  }

  @Override
  public boolean knowsNameFor(String id) {
    return "".equals(id);
  }
}