package net.sf.anathema.basics.eclipse.extension.fake;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

import org.easymock.EasyMock;

public class MockNamedChild implements IMockProp {

  private final String name;
  private final IExtensionElement child;

  public MockNamedChild(String name, IExtensionElement child) {
    this.name = name;
    this.child = child;
  }

  @Override
  public void configure(IExtensionElement element) throws ExtensionException {
    EasyMock.expect(element.getElement(name)).andStubReturn(child);
  }
}