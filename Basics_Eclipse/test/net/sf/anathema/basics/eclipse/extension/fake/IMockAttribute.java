package net.sf.anathema.basics.eclipse.extension.fake;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

public interface IMockAttribute {

  public void configure(IExtensionElement element) throws ExtensionException;
}