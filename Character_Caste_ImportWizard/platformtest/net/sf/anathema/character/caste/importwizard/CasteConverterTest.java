package net.sf.anathema.character.caste.importwizard;

import net.sf.anathema.character.importwizard.utility.ImportDocumentObjectMother;

import org.dom4j.Document;
import org.junit.Assert;
import org.junit.Test;

public class CasteConverterTest {

  private static final String PATH = "net/sf/anathema/character/caste/importwizard/"; //$NON-NLS-1$

  @Test
  public void createsCastes() throws Exception {
    Document document = ImportDocumentObjectMother.getDocumentFromFile(getClass(), PATH, "oldcharacter.ecg"); //$NON-NLS-1$
    Document expecteddocument = ImportDocumentObjectMother.getDocumentFromFile(getClass(), PATH, "newcaste.model"); //$NON-NLS-1$
    Document resultdocument = new CasteConverter().convert(document);
    Assert.assertEquals(expecteddocument.asXML(), resultdocument.asXML());
  }
}