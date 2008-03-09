package net.sf.anathema.basics.eclipse.extension.fake;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

import org.easymock.EasyMock;

public class MockNamedChildren implements IMockProp {

  private final IExtensionElement[] children;
  private final String name;

  public MockNamedChildren(String name, IExtensionElement... children) {
    this.name = name;
    this.children = children;
  }

  @Override
  public void configure(IExtensionElement element) throws ExtensionException {
    EasyMock.expect(element.getElements(name)).andStubReturn(children);
  }
}