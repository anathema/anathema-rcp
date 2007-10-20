package net.sf.anathema.basics.eclipse.extension.fake;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

import org.easymock.EasyMock;

public class IntegerMockAttribute implements IMockAttribute {

  private final String name;
  private final int value;

  public IntegerMockAttribute(String name, int value) {
    this.name = name;
    this.value = value;
  }
  
  @Override
  public void configure(IExtensionElement element) {
    EasyMock.expect(element.getIntegerAttribute(name)).andReturn(value).anyTimes();
  }
}