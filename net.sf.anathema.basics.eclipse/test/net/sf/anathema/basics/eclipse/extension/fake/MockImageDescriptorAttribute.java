package net.sf.anathema.basics.eclipse.extension.fake;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

import org.easymock.EasyMock;
import org.eclipse.jface.resource.ImageDescriptor;

public class MockImageDescriptorAttribute implements IMockProp {

  private String attributeName;
  private ImageDescriptor imageDescriptor;

  public MockImageDescriptorAttribute(String attributeName, ImageDescriptor imageDescriptor) {
    this.attributeName = attributeName;
    this.imageDescriptor = imageDescriptor;
  }

  @Override
  public void configure(IExtensionElement element) throws ExtensionException {
    EasyMock.expect(element.createImageDescriptorFromAttribute(attributeName)).andStubReturn(imageDescriptor);
  }
}