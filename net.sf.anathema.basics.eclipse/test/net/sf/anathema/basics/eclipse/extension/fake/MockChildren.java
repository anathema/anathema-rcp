package net.sf.anathema.basics.eclipse.extension.fake;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

import org.easymock.EasyMock;

public class MockChildren implements IMockProp {
  
  private final IExtensionElement[] children;

  public MockChildren(IExtensionElement... children) {
    this.children = children;
  }

  @Override
  public void configure(IExtensionElement element) throws ExtensionException {
    EasyMock.expect(element.getElements()).andStubReturn(children);
  }
}