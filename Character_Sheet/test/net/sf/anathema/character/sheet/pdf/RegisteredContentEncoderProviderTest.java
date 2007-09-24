package net.sf.anathema.character.sheet.pdf;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.logging.ILogger;
import net.sf.anathema.character.sheet.common.IDynamicPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.ICharacter;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class RegisteredContentEncoderProviderTest {

  private ICharacter character;
  private ILogger logger;

  @Before
  public void createProvider() throws Exception {
    this.character = EasyMock.createMock(ICharacter.class);
    this.logger = EasyMock.createMock(ILogger.class);
  }

  @Test
  public void noEncoderIsReturnedIfNoneIsConfigurated() throws Exception {
    RegisteredContentEncoderProvider provider = new RegisteredContentEncoderProvider(logger);
    assertNull(provider.getContentEncoder("myEncoderId", character)); //$NON-NLS-1$
  }

  @Test
  public void globalConfiguredEncoderIsReturnedForItsId() throws Exception {
    IPdfContentBoxEncoder encoder = EasyMock.createMock(IPdfContentBoxEncoder.class);
    IExtensionElement encoderElement = createElementForGlobalEncoder("myEncoderId", encoder); //$NON-NLS-1$
    IPluginExtension pluginExtension = ExtensionObjectMother.createPluginExtension(encoderElement);
    RegisteredContentEncoderProvider provider = new RegisteredContentEncoderProvider(logger, pluginExtension);
    assertSame(encoder, provider.getContentEncoder("myEncoderId", character)); //$NON-NLS-1$
  }

  @Test
  public void globalDynamicConfiguredEncoderIsReturnedForItsId() throws Exception {
    IPdfContentBoxEncoder encoder = EasyMock.createMock(IDynamicPdfContentBoxEncoder.class);
    IExtensionElement encoderElement = createElementForGlobalEncoder("myEncoderId", encoder); //$NON-NLS-1$
    IPluginExtension pluginExtension = ExtensionObjectMother.createPluginExtension(encoderElement);
    RegisteredContentEncoderProvider provider = new RegisteredContentEncoderProvider(logger, pluginExtension);
    assertSame(encoder, provider.getDynamicContentEncoder("myEncoderId", character)); //$NON-NLS-1$
  }

  @Test
  public void returnsDynamicEncoderWithDefaultHeightWhenNotIndynamicEncoderIsReturnedForId() throws Exception {
    IPdfContentBoxEncoder encoder = EasyMock.createMock(IPdfContentBoxEncoder.class);
    IExtensionElement encoderElement = createElementForGlobalEncoder("myEncoderId", encoder); //$NON-NLS-1$
    IPluginExtension pluginExtension = ExtensionObjectMother.createPluginExtension(encoderElement);
    RegisteredContentEncoderProvider provider = new RegisteredContentEncoderProvider(logger, pluginExtension);
    IDynamicPdfContentBoxEncoder resultEncoder = provider.getDynamicContentEncoder("myEncoderId", character); //$NON-NLS-1$
    assertSame(DefaultHeightDynamicEncoder.class, resultEncoder.getClass());
  }

  @Test
  public void returnsNoDynamicEncoderForUnconfiuredId() throws Exception {
    RegisteredContentEncoderProvider provider = new RegisteredContentEncoderProvider(logger);
    assertNull(provider.getDynamicContentEncoder("myEncoderId", character)); //$NON-NLS-1$
  }

  @Test
  public void logsErrorOnExeptionWhileEncoderRetrieval() throws Exception {
    ExtensionException exception = new ExtensionException("Hihi"); //$NON-NLS-1$
    logger.error(EasyMock.isA(String.class), EasyMock.same(exception));
    EasyMock.replay(logger);
    IExtensionElement encoderElement = createExceptionThrowingElement(exception, "myEncoderId"); //$NON-NLS-1$
    IPluginExtension pluginExtension = ExtensionObjectMother.createPluginExtension(encoderElement);
    RegisteredContentEncoderProvider provider = new RegisteredContentEncoderProvider(logger, pluginExtension);
    assertNull(provider.getDynamicContentEncoder("myEncoderId", character)); //$NON-NLS-1$
    EasyMock.verify(logger);
  }

  private IExtensionElement createExceptionThrowingElement(ExtensionException exception, String encoderId)
      throws ExtensionException {
    IExtensionElement element = EasyMock.createMock(IExtensionElement.class);
    EasyMock.expect(element.getAttribute("fieldId")).andReturn(encoderId).anyTimes(); //$NON-NLS-1$
    EasyMock.expect(element.getAttributeAsObject("class", IPdfContentBoxEncoder.class)).andThrow(exception).anyTimes(); //$NON-NLS-1$
    EasyMock.replay(element);
    return element;
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