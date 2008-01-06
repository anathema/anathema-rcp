package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterTemplate;

public class NullModelDescriptor extends AbstractExecutableExtension implements IModelDescriptor {

  @Override
  public boolean isSupportedBy(ICharacterTemplate template) {
    return false;
  }
}