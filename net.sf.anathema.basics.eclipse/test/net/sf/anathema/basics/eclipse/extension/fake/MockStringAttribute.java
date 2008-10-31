package net.sf.anathema.basics.eclipse.extension.fake;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

import org.easymock.EasyMock;

public class MockStringAttribute implements IMockProp {

  private final String name;
  private final String value;

  public MockStringAttribute(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public void configure(IExtensionElement element) {
    EasyMock.expect(element.getAttribute(name)).andReturn(value).anyTimes();
    EasyMock.expect(element.hasAttribute(name)).andReturn(true).anyTimes();
  }
}