package net.sf.anathema.character.importwizard;

import net.sf.anathema.character.importwizard.XSLCharacterConverter;

import org.dom4j.Document;
import org.junit.Assert;
import org.junit.Test;

public class AttributeConversionTest {

  @Test
  public void createsAttributes() throws Exception {
    DocumentProvider documentProvider = new DocumentProvider(getClass());
    Document document = documentProvider.readDocument("oldcharacter.ecg"); //$NON-NLS-1$
    Document expecteddocument = documentProvider.readDocument("newattributes.model"); //$NON-NLS-1$
    Document resultdocument = XSLCharacterConverter.convertAttributes(document);
    Assert.assertEquals(expecteddocument.asXML(), resultdocument.asXML());
  }
}