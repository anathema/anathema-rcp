package net.sf.anathema.character.sheet.pdf;

import static org.junit.Assert.*;
import net.sf.anathema.character.sheet.common.IDynamicPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.sheet.content.ICharacter;
import net.sf.anathema.character.sheet.content.IContentEncoderProvider;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class ContentEncoderProviderTest {

  private IContentEncoderProvider createRegisteredProvider() {
    IContentEncoderProvider registeredProviders = EasyMock.createNiceMock(IContentEncoderProvider.class);
    EasyMock.expect(registeredProviders.getContentEncoder(ENCODER, character)).andReturn(encoder).anyTimes();
    EasyMock.expect(registeredProviders.getDynamicContentEncoder(ENCODER, character))
        .andReturn(dynamicEncoder)
        .anyTimes();
    EasyMock.replay(registeredProviders);
    return registeredProviders;
  }

  private static final String ENCODER = "registeredEncoder"; //$NON-NLS-1$
  private IContentEncoderProvider contentProvider;
  private ICharacter character;
  private IPdfContentBoxEncoder encoder;
  private IDynamicPdfContentBoxEncoder dynamicEncoder;

  @Before
  public void createProvider() throws Exception {
    character = EasyMock.createMock(ICharacter.class);
    encoder = EasyMock.createMock(IPdfContentBoxEncoder.class);
    dynamicEncoder = EasyMock.createMock(IDynamicPdfContentBoxEncoder.class);
    contentProvider = new ContentEncoderProvider(createRegisteredProvider());
  }

  @Test
  public void returnsRegisteredEncoderForItsId() throws Exception {
    assertSame(encoder, contentProvider.getContentEncoder(ENCODER, character));
  }

  @Test
  public void returnsRegisteredDynamicEncoderForItsId() throws Exception {
    assertSame(dynamicEncoder, contentProvider.getDynamicContentEncoder(ENCODER, character));
  }

  @Test
  public void returnsPdfHorizontalLineEncoderForUnregisteredId() throws Exception {
    IPdfContentBoxEncoder contentEncoder = contentProvider.getContentEncoder("unregisteredEncoder", character); //$NON-NLS-1$
    assertNotSame(encoder, contentEncoder);
    assertSame(PdfHorizontalLineContentEncoder.class, contentEncoder.getClass());
  }

  @Test
  public void unregisteredEncoderHasIdAsHeader() throws Exception {
    IPdfContentBoxEncoder contentEncoder = contentProvider.getContentEncoder("unregisteredEncoder", character); //$NON-NLS-1$
    assertEquals("unregisteredEncoder", contentEncoder.getHeader()); //$NON-NLS-1$

  }

  @Test
  public void returnsDynamicPdfHorizontalLineEncoderForUnregisteredId() throws Exception {
    IPdfContentBoxEncoder contentEncoder = contentProvider.getDynamicContentEncoder("unregisteredEncoder", character); //$NON-NLS-1$
    assertNotSame(encoder, contentEncoder);
    assertSame(DynamicPdfHorizontalLineContentEncoder.class, contentEncoder.getClass());
    assertEquals("unregisteredEncoder", contentEncoder.getHeader()); //$NON-NLS-1$
  }

  @Test
  public void unregisteredDynamicEncoderHasIdAsHeader() throws Exception {
    IDynamicPdfContentBoxEncoder contentEncoder = contentProvider.getDynamicContentEncoder(
        "unregisteredEncoder", character); //$NON-NLS-1$
    assertEquals("unregisteredEncoder", contentEncoder.getHeader()); //$NON-NLS-1$

  }

}