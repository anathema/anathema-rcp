package net.sf.anathema.character.textreport;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.character.textreport.encoder.ITextReportEncoder;
import net.sf.anathema.character.textreport.encoder.TextEncoderExtensionPoint;

import org.junit.Before;
import org.junit.Test;

public class TextEncoderExtensionPoint_OrderTest {

  private List<ITextReportEncoder> encoders;
  private ITextReportEncoder tailingEncoder;
  private ITextReportEncoder leadingEncoder;

  @Before
  public void createEncodersFromEmptyExtensionPoint() throws ExtensionException {
    tailingEncoder = createMock(ITextReportEncoder.class);
    leadingEncoder = createMock(ITextReportEncoder.class);
    IPluginExtension pluginExtension = ExtensionObjectMother.createPluginExtension(
        TextEncoderElementObjectMother.create(tailingEncoder, "tailing", "leading"), //$NON-NLS-1$ //$NON-NLS-2$
        TextEncoderElementObjectMother.create(leadingEncoder, "leading", null)); //$NON-NLS-1$
    encoders = new TextEncoderExtensionPoint(pluginExtension).getEncoders();
  }

  @Test
  public void leadingIsFirstEncoder() throws Exception {
    assertEquals(leadingEncoder, encoders.get(0));
  }

  @Test
  public void tailingIsSecondEncoder() throws Exception {
    assertEquals(tailingEncoder, encoders.get(1));
  }
}