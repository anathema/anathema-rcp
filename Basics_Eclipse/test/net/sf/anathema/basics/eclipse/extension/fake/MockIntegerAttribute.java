package net.sf.anathema.basics.eclipse.extension.fake;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

import org.easymock.EasyMock;

public class MockIntegerAttribute implements IMockProp {

  private final String name;
  private final int value;

  public MockIntegerAttribute(String name, int value) {
    this.name = name;
    this.value = value;
  }
  
  @Override
  public void configure(IExtensionElement element) {
    EasyMock.expect(element.getIntegerAttribute(name)).andReturn(value).anyTimes();
    EasyMock.expect(element.hasAttribute(name)).andReturn(true).anyTimes();
  }
}