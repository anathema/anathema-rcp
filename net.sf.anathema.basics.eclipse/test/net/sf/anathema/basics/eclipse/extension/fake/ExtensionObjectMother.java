package net.sf.anathema.basics.eclipse.extension.fake;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;

import org.easymock.EasyMock;

public class ExtensionObjectMother {

  public static IExtensionPoint createEmptyExtensionPoint() {
    return createExtensionPoint(new IPluginExtension[0]);
  }

  public static IExtensionPoint createExtensionPoint(IPluginExtension... pluginExtensions) {
    return new DummyExtensionPointExtension(pluginExtensions);
  }

  public static IExtensionPoint createExtensionPoint(IExtensionElement... elements) {
    IPluginExtension pluginExtension = createPluginExtension(elements);
    return new DummyExtensionPointExtension(pluginExtension);
  }

  public static IPluginExtension createPluginExtension(IExtensionElement... elements) {
    IPluginExtension pluginExtension = EasyMock.createMock(IPluginExtension.class);
    EasyMock.expect(pluginExtension.getElements()).andReturn(elements).anyTimes();
    EasyMock.replay(pluginExtension);
    return pluginExtension;
  }

  public static IExtensionElement createExtensionElement(IMockProp... attributes) throws ExtensionException {
    IExtensionElement element = EasyMock.createNiceMock(IExtensionElement.class);
    for (IMockProp attribute : attributes) {
      attribute.configure(element);
    }
    EasyMock.replay(element);
    return element;
  }
}