package net.sf.anathema.character.sheet.pdf;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.sheet.common.IDynamicPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class RegisteredContentEncoderProviderTest {

  private IModelContainer character;

  @Before
  public void createProvider() throws Exception {
    this.character = EasyMock.createMock(ICharacter.class);
  }

  @Test
  public void noEncoderIsReturnedIfNoneIsConfigurated() throws Exception {
    RegisteredContentEncoderProvider provider = new RegisteredContentEncoderProvider(new IPluginExtension[0]);
    assertNull(provider.getContentEncoder("myEncoderId", character)); //$NON-NLS-1$
  }

  @Test
  public void globalConfiguredEncoderIsReturnedForItsId() throws Exception {
    IPdfContentBoxEncoder encoder = EasyMock.createMock(IPdfContentBoxEncoder.class);
    IExtensionElement encoderElement = createElementForGlobalEncoder("myEncoderId", encoder); //$NON-NLS-1$
    IPluginExtension pluginExtension = ExtensionObjectMother.createPluginExtension(encoderElement);
    RegisteredContentEncoderProvider provider = new RegisteredContentEncoderProvider(pluginExtension);
    assertSame(encoder, provider.getContentEncoder("myEncoderId", character)); //$NON-NLS-1$
  }

  @Test
  public void globalDynamicConfiguredEncoderIsReturnedForItsId() throws Exception {
    IPdfContentBoxEncoder encoder = EasyMock.createMock(IDynamicPdfContentBoxEncoder.class);
    IExtensionElement encoderElement = createElementForGlobalEncoder("myEncoderId", encoder); //$NON-NLS-1$
    IPluginExtension pluginExtension = ExtensionObjectMother.createPluginExtension(encoderElement);
    RegisteredContentEncoderProvider provider = new RegisteredContentEncoderProvider(pluginExtension);
    assertSame(encoder, provider.getDynamicContentEncoder("myEncoderId", character)); //$NON-NLS-1$
  }

  @Test
  public void returnsDynamicEncoderWithDefaultHeightWhenNotIndynamicEncoderIsReturnedForId() throws Exception {
    IPdfContentBoxEncoder encoder = EasyMock.createMock(IPdfContentBoxEncoder.class);
    IExtensionElement encoderElement = createElementForGlobalEncoder("myEncoderId", encoder); //$NON-NLS-1$
    IPluginExtension pluginExtension = ExtensionObjectMother.createPluginExtension(encoderElement);
    RegisteredContentEncoderProvider provider = new RegisteredContentEncoderProvider(pluginExtension);
    IDynamicPdfContentBoxEncoder resultEncoder = provider.getDynamicContentEncoder("myEncoderId", character); //$NON-NLS-1$
    assertSame(DefaultHeightDynamicEncoder.class, resultEncoder.getClass());
  }

  @Test
  public void returnsNoDynamicEncoderForUnconfiuredId() throws Exception {
    RegisteredContentEncoderProvider provider = new RegisteredContentEncoderProvider(new IPluginExtension[0]);
    assertNull(provider.getDynamicContentEncoder("myEncoderId", character)); //$NON-NLS-1$
  }

  private IExtensionElement createElementForGlobalEncoder(String encoderId, IPdfContentBoxEncoder encoder)
      throws ExtensionException {
    IExtensionElement element = EasyMock.createMock(IExtensionElement.class);
    EasyMock.expect(element.getAttribute("fieldId")).andReturn(encoderId).anyTimes(); //$NON-NLS-1$
    EasyMock.expect(element.getAttributeAsObject("class", IPdfContentBoxEncoder.class)).andReturn(encoder).anyTimes(); //$NON-NLS-1$
    EasyMock.replay(element);
    return element;
  }
}