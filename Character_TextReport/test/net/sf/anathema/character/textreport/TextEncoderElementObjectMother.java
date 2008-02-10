package net.sf.anathema.character.textreport;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.MockChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockExecutableExtensionAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.MockName;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.character.textreport.encoder.ITextReportEncoder;

public class TextEncoderElementObjectMother {


  public static IExtensionElement create(ITextReportEncoder encoder, String id, String afterId)
      throws ExtensionException {
    MockName afterName = new MockName("after"); //$NON-NLS-1$
    MockStringAttribute referenceAttribute = new MockStringAttribute("referenceId", afterId); //$NON-NLS-1$
    IExtensionElement afterElement = ExtensionObjectMother.createExtensionElementWithAttributes(
        afterName,
        referenceAttribute);
    return ExtensionObjectMother.createExtensionElementWithAttributes(
        createEncoderAttribute(encoder),
        afterId == null ? new MockChildren() : new MockChildren(afterElement),
        new MockStringAttribute("id", id)); //$NON-NLS-1$
  }

  private static MockExecutableExtensionAttribute<ITextReportEncoder> createEncoderAttribute(ITextReportEncoder encoder) {
    return new MockExecutableExtensionAttribute<ITextReportEncoder>("class", ITextReportEncoder.class, encoder); //$NON-NLS-1$
  }
}