package net.sf.anathema.character.textreport;

import static org.junit.Assert.*;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.MockErroneousExecutableExtensionAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.MockExecutableExtensionAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.IMockProp;
import net.sf.anathema.character.textreport.encoder.ITextReportEncoder;
import net.sf.anathema.character.textreport.encoder.TextEncoderExtensionPoint;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class TextEncoderExtensionPoint_FilledTest {

  private List<ITextReportEncoder> encoders;
  private ITextReportEncoder encoder;

  @Before
  public void createEncodersFromEmptyExtensionPoint() throws ExtensionException {
    encoder = EasyMock.createMock(ITextReportEncoder.class);
    IMockProp legalAttribute = new MockExecutableExtensionAttribute<ITextReportEncoder>(
        "class", //$NON-NLS-1$
        ITextReportEncoder.class,
        encoder);
    MockErroneousExecutableExtensionAttribute<ITextReportEncoder> illegalAttribute = new MockErroneousExecutableExtensionAttribute<ITextReportEncoder>(
        "class", //$NON-NLS-1$
        ITextReportEncoder.class);
    IPluginExtension pluginExtension = ExtensionObjectMother.createPluginExtension(
        ExtensionObjectMother.createExtensionElementWithAttributes(legalAttribute),
        ExtensionObjectMother.createExtensionElementWithAttributes(illegalAttribute));
    encoders = new TextEncoderExtensionPoint(pluginExtension).getEncoders();
  }

  @Test
  public void oneEncoderFund() throws Exception {
    assertEquals(1, encoders.size());
  }

  @Test
  public void encoderIsFound() throws Exception {
    assertSame(encoder, encoders.get(0));
  }
}