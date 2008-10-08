package net.sf.anathema.character.core.model.display;

import org.eclipse.core.runtime.IExecutableExtension;

public interface IDisplayAddition extends IExecutableExtension {

  public void configure(IConfigurableViewElement modelViewElement);
}