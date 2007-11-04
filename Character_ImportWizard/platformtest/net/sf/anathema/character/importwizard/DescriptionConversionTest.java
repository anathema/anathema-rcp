package net.sf.anathema.character.importwizard;

import java.io.InputStream;

import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.junit.Assert;
import org.junit.Test;

public class DescriptionConversionTest {
  private static final String PATH = "net/sf/anathema/character/core/importwizard/"; //$NON-NLS-1$

  @Test
  public void createsHierarchy() throws Exception {
    Document document = readDocument("oldcharacter.ecg"); //$NON-NLS-1$
    Document expecteddocument = readDocument("newbasic.description"); //$NON-NLS-1$
    Document resultdocument = XSLCharacterConverter.convertDescription(document);
    Assert.assertEquals(DocumentUtilities.asString(expecteddocument), DocumentUtilities.asString(resultdocument));
  }

  private Document readDocument(String string) throws PersistenceException {
    return DocumentUtilities.read(getStream(string));
  }

  private InputStream getStream(String string) {
    return getClass().getClassLoader().getResourceAsStream(PATH + string);
  }
}
