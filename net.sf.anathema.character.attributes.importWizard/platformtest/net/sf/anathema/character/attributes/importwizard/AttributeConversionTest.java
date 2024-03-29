package net.sf.anathema.character.attributes.importwizard;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.MockResourceAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.character.attributes.AttributesPlugin;
import net.sf.anathema.character.importwizard.XslDocumentConverter;
import net.sf.anathema.character.importwizard.utility.ImportDocumentObjectMother;

import org.custommonkey.xmlunit.Diff;
import org.dom4j.Document;
import org.junit.Test;

public class AttributeConversionTest {

  private static final String PATH = "net/sf/anathema/character/attributes/importwizard/"; //$NON-NLS-1$

  @Test
  public void createsAttributes() throws Exception {
    Document document = ImportDocumentObjectMother.getDocumentFromFile(getClass(), PATH, "oldcharacter.ecg"); //$NON-NLS-1$
    Document expecteddocument = ImportDocumentObjectMother.getDocumentFromFile(getClass(), PATH, "newattributes.model"); //$NON-NLS-1$
    IExtensionElement element = ExtensionObjectMother.createExtensionElement(new MockResourceAttribute(
        "stylesheet", //$NON-NLS-1$
        new File("xsl/AttributeCreation.xsl")), new MockStringAttribute("bundle", AttributesPlugin.ID)); //$NON-NLS-1$ //$NON-NLS-2$
    Document resultdocument = new XslDocumentConverter(element).convert(document);
    Diff diff = new Diff(resultdocument.asXML(), expecteddocument.asXML());
    assertThat(diff.similar(), is(true));
  }
}