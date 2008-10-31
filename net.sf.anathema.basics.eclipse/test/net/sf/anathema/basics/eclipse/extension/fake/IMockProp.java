package net.sf.anathema.basics.eclipse.extension.fake;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.test.behaviour.IMockBehaviour;

public interface IMockProp extends IMockBehaviour<IExtensionElement>{

  public void configure(IExtensionElement element) throws ExtensionException;
}