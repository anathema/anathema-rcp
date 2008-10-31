package net.sf.anathema.basics.eclipse.extension.fake;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

import org.easymock.EasyMock;
import org.eclipse.core.runtime.IExecutableExtension;

public class MockExecutableExtensionAttribute<E extends IExecutableExtension> implements IMockProp {

  private final String name;
  private final Class<E> clazz;
  private final E value;

  public MockExecutableExtensionAttribute(String name, Class<E> clazz, E value) {
    this.name = name;
    this.clazz = clazz;
    this.value = value;
  }

  @Override
  public void configure(IExtensionElement element) throws ExtensionException {
    EasyMock.expect(element.getAttributeAsObject(name, clazz)).andReturn(value).anyTimes();
  }
}