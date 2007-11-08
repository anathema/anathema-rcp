package net.sf.anathema.character.attributes.importwizard;

import net.sf.anathema.character.attributes.importwizard.AttributesConverter;
import net.sf.anathema.character.importwizard.utility.ImportDocumentObjectMother;

import org.dom4j.Document;
import org.junit.Assert;
import org.junit.Test;

public class AttributeConversionTest {

  @Test
  public void createsAttributes() throws Exception {
    Document document = ImportDocumentObjectMother.getDocumentFromFile(
        getClass(),
        "net/sf/anathema/character/importwizard/", //$NON-NLS-1$
        "oldcharacter.ecg"); //$NON-NLS-1$
    Document expecteddocument = ImportDocumentObjectMother.getDocumentFromFile(
        getClass(),
        "net/sf/anathema/character/importwizard/", //$NON-NLS-1$
        "newattributes.model"); //$NON-NLS-1$
    Document resultdocument = AttributesConverter.convertAttributes(document);
    Assert.assertEquals(expecteddocument.asXML(), resultdocument.asXML());
  }
}