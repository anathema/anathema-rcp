package net.sf.anathema.character.textreport;

import static org.junit.Assert.*;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.character.textreport.encoder.ITextReportEncoder;
import net.sf.anathema.character.textreport.encoder.TextEncoderExtensionPoint;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class TextEncoderExtensionPoint_MultipleTest {

  private List<ITextReportEncoder> encoders;
  private ITextReportEncoder firstEncoder;
  private ITextReportEncoder secondEncoder;

  @Before
  public void createEncodersFromEmptyExtensionPoint() throws ExtensionException {
    firstEncoder = EasyMock.createMock(ITextReportEncoder.class);
    secondEncoder = EasyMock.createMock(ITextReportEncoder.class);
    IPluginExtension pluginExtension = ExtensionObjectMother.createPluginExtension(
        TextEncoderElementObjectMother.create(firstEncoder, null, null),
        TextEncoderElementObjectMother.create(secondEncoder, null, null));
    encoders = new TextEncoderExtensionPoint(pluginExtension).getEncoders();
  }

  @Test
  public void twoEncodersFound() throws Exception {
    assertEquals(2, encoders.size());
  }

  @Test
  public void firstEncoderIsFoundFirst() throws Exception {
    assertSame(firstEncoder, encoders.get(0));
  }

  @Test
  public void secondEncoderIsFoundSecond() throws Exception {
    assertSame(secondEncoder, encoders.get(1));
  }
}