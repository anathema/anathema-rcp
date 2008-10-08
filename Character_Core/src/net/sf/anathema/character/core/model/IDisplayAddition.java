package net.sf.anathema.character.core.model;

import org.eclipse.core.runtime.IExecutableExtension;

public interface IDisplayAddition extends IExecutableExtension {

  public void configure(IConfigurableViewElement modelViewElement);
}