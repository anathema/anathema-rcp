package net.sf.anathema.character.textreport;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.MockChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockExecutableExtensionAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.MockName;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.character.textreport.encoder.ITextReportEncoder;
import net.sf.anathema.character.textreport.encoder.TextEncoderExtensionPoint;

import org.junit.Before;
import org.junit.Test;

public class TextEncoderExtensionPoint_RecursiveOrderTest {

  private List<ITextReportEncoder> encoders;
  private ITextReportEncoder tailingEncoder;
  private ITextReportEncoder leadingEncoder;

  @Before
  public void createEncodersFromEmptyExtensionPoint() throws ExtensionException {
    tailingEncoder = createMock(ITextReportEncoder.class);
    leadingEncoder = createMock(ITextReportEncoder.class);
    IExtensionElement afterLeadingElement = ExtensionObjectMother.createExtensionElementWithAttributes(new MockName(
        "after"), new MockStringAttribute("referenceId", "leading")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    IPluginExtension pluginExtension = ExtensionObjectMother.createPluginExtension(
        ExtensionObjectMother.createExtensionElementWithAttributes(
            createEncoderAttribute(tailingEncoder),
            new MockStringAttribute("id", "tailing"), //$NON-NLS-1$ //$NON-NLS-2$
            new MockChildren(afterLeadingElement)),
        ExtensionObjectMother.createExtensionElementWithAttributes(
            createEncoderAttribute(leadingEncoder),
            new MockChildren(),
            new MockStringAttribute("id", "leading"))); //$NON-NLS-1$ //$NON-NLS-2$
    encoders = new TextEncoderExtensionPoint(pluginExtension).getEncoders();
  }

  private MockExecutableExtensionAttribute<ITextReportEncoder> createEncoderAttribute(ITextReportEncoder encoder) {
    return new MockExecutableExtensionAttribute<ITextReportEncoder>("class", ITextReportEncoder.class, encoder); //$NON-NLS-1$
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