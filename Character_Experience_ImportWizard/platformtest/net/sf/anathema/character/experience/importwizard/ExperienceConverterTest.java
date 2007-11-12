package net.sf.anathema.character.experience.importwizard;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.importwizard.utility.ImportDocumentObjectMother;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.junit.Before;
import org.junit.Test;

public class ExperienceConverterTest {

  private ExperienceConverter converter;

  @Before
  public void createConverter() throws Exception {
    this.converter = new ExperienceConverter();
  }

  @Test
  public void createsDescription() throws Exception {
    Document document = ImportDocumentObjectMother.getDocumentFromFile(
        getClass(),
        "net/sf/anathema/character/experience/importwizard/", //$NON-NLS-1$
        "oldcharacter.ecg"); //$NON-NLS-1$
    Document expecteddocument = ImportDocumentObjectMother.getDocumentFromFile(
        getClass(),
        "net/sf/anathema/character/experience/importwizard/", "newexperience.model"); //$NON-NLS-1$ //$NON-NLS-2$
    Document resultdocument = converter.convert(document);
    assertEquals(DocumentUtilities.asString(expecteddocument), DocumentUtilities.asString(resultdocument));
  }
}