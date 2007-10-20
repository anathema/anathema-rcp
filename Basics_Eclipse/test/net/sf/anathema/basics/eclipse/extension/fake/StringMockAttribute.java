package net.sf.anathema.basics.eclipse.extension.fake;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

import org.easymock.EasyMock;

public class StringMockAttribute implements IMockAttribute {

  private final String name;
  private final String value;

  public StringMockAttribute(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public void configure(IExtensionElement element) {
    EasyMock.expect(element.getAttribute(name)).andReturn(value).anyTimes();
  }
}