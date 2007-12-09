package net.sf.anathema.character.textreport;

import static org.junit.Assert.*;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.character.textreport.encoder.ITextReportEncoder;

import org.junit.Before;
import org.junit.Test;

public class TextEncoderExtensionPoint_EmptyTest {

  private List<ITextReportEncoder> encoders;

  @Before
  public void createEncodersFromEmptyExtensionPoint() {
    encoders = new TextEncoderExtensionPoint(ExtensionObjectMother.createPluginExtension()).getEncoders();
  }

  @Test
  public void noEncodersFoundForEmptyExtension() throws Exception {
    assertEquals(0, encoders.size());
  }
}