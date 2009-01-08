package net.sf.anathema.basics.eclipse.extension.fake;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

import org.easymock.EasyMock;

public class MockResourceAttribute implements IMockProp {

  private final String name;
  private final URL value;

  public MockResourceAttribute(String name, File file) throws MalformedURLException {
    this.name = name;
    this.value = file.toURI().toURL();
  }

  @Override
  public void configure(IExtensionElement element) throws ExtensionException {
    EasyMock.expect(element.getResourceAttribute(name)).andReturn(value).anyTimes();
    EasyMock.expect(element.hasAttribute(name)).andReturn(true).anyTimes();
  }
}