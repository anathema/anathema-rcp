package net.sf.anathema.character.core.model.content;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.IModelContainer;

public class NullModelContentCheck extends UnconfiguredExecutableExtension implements IModelContentCheck {

  @Override
  public boolean evaluate(IModelContainer container, String content) {
    return false;
  }
}