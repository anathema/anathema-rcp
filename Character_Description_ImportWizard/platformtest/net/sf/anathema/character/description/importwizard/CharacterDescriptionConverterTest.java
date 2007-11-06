package net.sf.anathema.character.description.importwizard;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.importwizard.utility.ImportDocumentObjectMother;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.junit.Before;
import org.junit.Test;

public class CharacterDescriptionConverterTest {

  private DescriptionConverter converter;

  @Before
  public void createConverter() throws Exception {
    this.converter = new DescriptionConverter();
  }

  @Test
  public void createsDescription() throws Exception {
    Document document = ImportDocumentObjectMother.getDocumentFromFile(
        getClass(),
        "net/sf/anathema/character/description/importwizard/", //$NON-NLS-1$
        "oldcharacter.ecg"); //$NON-NLS-1$
    Document expecteddocument = ImportDocumentObjectMother.getDocumentFromFile(
        getClass(),
        "net/sf/anathema/character/description/importwizard/", "newbasic.description"); //$NON-NLS-1$ //$NON-NLS-2$
    Document resultdocument = converter.convert(document);
    assertEquals(DocumentUtilities.asString(expecteddocument), DocumentUtilities.asString(resultdocument));
  }

  @Test
  public void createsDescriptionIfElementsAreMissing() throws Exception {
    Document resultdocument = converter.convert(DescriptionDocumentObjectMother.createEmptyDescriptionDocument());
    Document expecteddocument = DescriptionDocumentObjectMother.createEmptyVersionedModelDocument();
    assertEquals(DocumentUtilities.asString(expecteddocument), DocumentUtilities.asString(resultdocument));
  }
}