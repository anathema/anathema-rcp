package net.sf.anathema.basics.eclipse.extension.fake;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

import org.easymock.EasyMock;
import org.eclipse.core.runtime.IExecutableExtension;

public class ErroneousExecutableExtensionMockAttribute<E extends IExecutableExtension> implements IMockAttribute {

  private final String name;
  private final Class<E> clazz;

  public ErroneousExecutableExtensionMockAttribute(String name, Class<E> clazz) {
    this.name = name;
    this.clazz = clazz;
  }

  @Override
  public void configure(IExtensionElement element) throws ExtensionException {
    EasyMock.expect(element.getAttributeAsObject(name, clazz)).andThrow(new ExtensionException());
  }
}