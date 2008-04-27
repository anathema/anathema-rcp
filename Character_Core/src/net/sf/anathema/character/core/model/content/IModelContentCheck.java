package net.sf.anathema.character.core.model.content;

import net.sf.anathema.character.core.character.IModelContainer;

import org.eclipse.core.runtime.IExecutableExtension;

public interface IModelContentCheck extends IExecutableExtension {

  public boolean evaluate(IModelContainer container, String content);
}