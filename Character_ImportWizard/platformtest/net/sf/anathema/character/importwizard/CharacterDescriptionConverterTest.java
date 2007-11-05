package net.sf.anathema.character.importwizard;

import static org.junit.Assert.assertEquals;
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
    ImportDocumentObjectMother provider = new ImportDocumentObjectMother(getClass());
    Document document = provider.readDocument("oldcharacter.ecg"); //$NON-NLS-1$
    Document expecteddocument = provider.readDocument("newbasic.description"); //$NON-NLS-1$
    Document resultdocument = converter.convert(document);
    assertEquals(DocumentUtilities.asString(expecteddocument), DocumentUtilities.asString(resultdocument));
  }

  @Test
  public void createsDescriptionIfElementsAreMissing() throws Exception {
    Document resultdocument = converter.convert(ImportDocumentObjectMother.createEmptyDescriptionDocument());
    Document expecteddocument = ImportDocumentObjectMother.createEmptyVersionedModelDocument();
    assertEquals(DocumentUtilities.asString(expecteddocument), DocumentUtilities.asString(resultdocument));
  }
}