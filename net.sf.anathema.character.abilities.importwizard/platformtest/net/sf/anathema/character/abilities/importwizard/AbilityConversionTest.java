package net.sf.anathema.character.abilities.importwizard;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.MockResourceAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.importwizard.XslDocumentConverter;
import net.sf.anathema.character.importwizard.utility.ImportDocumentObjectMother;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.dom4j.Document;
import org.junit.Test;
import org.xml.sax.SAXException;

public class AbilityConversionTest {

  private static final String PATH = "net/sf/anathema/character/abilities/importwizard/"; //$NON-NLS-1$

  @Test
  public void createsAbilities() throws Exception {
    Document document = ImportDocumentObjectMother.getDocumentFromFile(getClass(), PATH, "oldcharacter.ecg"); //$NON-NLS-1$
    Document expecteddocument = ImportDocumentObjectMother.getDocumentFromFile(getClass(), PATH, "newabilities.model"); //$NON-NLS-1$
    IExtensionElement element = ExtensionObjectMother.createExtensionElementWithAttributes(new MockResourceAttribute(
        "stylesheet", //$NON-NLS-1$
        new File("xsl/AbilityCreation.xsl")), new MockStringAttribute("bundle", IAbilitiesPluginConstants.PLUGIN_ID)); //$NON-NLS-1$ //$NON-NLS-2$
    Document resultdocument = new XslDocumentConverter(element).convert(document);
    XMLUnit.setIgnoreWhitespace(true);
    Diff diff = createDiff(expecteddocument, resultdocument);
    assertThat(createDiff(expecteddocument, resultdocument).toString(), diff.similar(), is(true));
  }

  private Diff createDiff(Document expecteddocument, Document resultdocument) throws SAXException, IOException {
    return new Diff(expecteddocument.asXML(), resultdocument.asXML());
  }
}