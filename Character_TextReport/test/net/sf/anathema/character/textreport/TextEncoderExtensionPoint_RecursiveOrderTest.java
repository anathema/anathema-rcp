package net.sf.anathema.character.textreport;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.character.textreport.encoder.ITextReportEncoder;
import net.sf.anathema.character.textreport.encoder.TextEncoderExtensionPoint;

import org.junit.Before;
import org.junit.Test;

public class TextEncoderExtensionPoint_RecursiveOrderTest {

  private static final String FIRST_ID = "first"; //$NON-NLS-1$
  private static final String SECOND_ID = "second"; //$NON-NLS-1$
  private static final String THIRD_ID = "third"; //$NON-NLS-1$
  private List<ITextReportEncoder> encoders;
  private ITextReportEncoder secondEncoder;
  private ITextReportEncoder firstEncoder;
  private ITextReportEncoder thirdEncoder;

  @Before
  public void createEncodersFromEmptyExtensionPoint() throws ExtensionException {
    firstEncoder = createMock(ITextReportEncoder.class);
    secondEncoder = createMock(ITextReportEncoder.class);
    thirdEncoder = createMock(ITextReportEncoder.class);
    IExtensionElement firstElement = TextEncoderElementObjectMother.create(firstEncoder, FIRST_ID, SECOND_ID);
    IExtensionElement secondElement = TextEncoderElementObjectMother.create(secondEncoder, SECOND_ID, THIRD_ID);
    IExtensionElement thirdElement = TextEncoderElementObjectMother.create(thirdEncoder, THIRD_ID, FIRST_ID);
    IPluginExtension pluginExtension = ExtensionObjectMother.createPluginExtension(
        firstElement,
        secondElement,
        thirdElement);
    encoders = new TextEncoderExtensionPoint(pluginExtension).getEncoders();
  }

  @Test
  public void findsTwoEncoders() throws Exception {
    assertEquals(3, encoders.size());
  }

  @Test
  public void containsFirstEncoder() throws Exception {
    assertTrue(encoders.contains(firstEncoder));
  }

  @Test
  public void containsSecondEncoder() throws Exception {
    assertTrue(encoders.contains(secondEncoder));
  }

  @Test
  public void containsThirdEncoder() throws Exception {
    assertTrue(encoders.contains(thirdEncoder));
  }
}