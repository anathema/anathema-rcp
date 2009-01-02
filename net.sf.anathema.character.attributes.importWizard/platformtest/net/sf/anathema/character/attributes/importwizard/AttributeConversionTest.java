package net.sf.anathema.character.attributes.importwizard;

import net.sf.anathema.character.attributes.AttributesPlugin;
import net.sf.anathema.character.importwizard.XslDocumentConverter;
import net.sf.anathema.character.importwizard.utility.ImportDocumentObjectMother;

import org.dom4j.Document;
import org.junit.Assert;
import org.junit.Test;

public class AttributeConversionTest {

  private static final String PATH = "net/sf/anathema/character/attributes/importwizard/"; //$NON-NLS-1$

  @Test
  public void createsAttributes() throws Exception {
    Document document = ImportDocumentObjectMother.getDocumentFromFile(getClass(), PATH, "oldcharacter.ecg"); //$NON-NLS-1$
    Document expecteddocument = ImportDocumentObjectMother.getDocumentFromFile(getClass(), PATH, "newattributes.model"); //$NON-NLS-1$
    Document resultdocument = new XslDocumentConverter(
        "xsl/AttributeCreation.xsl",
        AttributesPlugin.ID,
        Messages.AttributesImporter_ErrorMessage).convert(document);
    Assert.assertEquals(expecteddocument.asXML(), resultdocument.asXML());
  }
}