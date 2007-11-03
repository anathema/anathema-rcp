package net.sf.anathema.character.core.importwizard;

import java.io.InputStream;

import net.sf.anathema.character.importwizard.XSLCharacterConverter;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.junit.Assert;
import org.junit.Test;

public class AttributeConversionTest {
  private static final String PATH = "net/sf/anathema/character/core/importwizard/"; //$NON-NLS-1$

  @Test
  public void createsHierarchy() throws Exception {
    Document document = readDocument("oldcharacter.ecg"); //$NON-NLS-1$
    Document expecteddocument = readDocument("newattributes.model"); //$NON-NLS-1$
    Document resultdocument = XSLCharacterConverter.convertAttributes(document);
    Assert.assertEquals(expecteddocument.asXML(), resultdocument.asXML());
  }

  private Document readDocument(String string) throws PersistenceException {
    return DocumentUtilities.read(getStream(string));
  }

  private InputStream getStream(String string) {
    return getClass().getClassLoader().getResourceAsStream(PATH + string);
  }
}
