package net.sf.anathema.basics.eclipse.extension.fake;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;

import org.easymock.EasyMock;

public class ExtensionObjectMother {

  public static IPluginExtension createPluginExtension(IExtensionElement... elements) {
    IPluginExtension pluginExtension = EasyMock.createMock(IPluginExtension.class);
    EasyMock.expect(pluginExtension.getElements()).andReturn(elements).anyTimes();
    EasyMock.replay(pluginExtension);
    return pluginExtension;
  }

}
