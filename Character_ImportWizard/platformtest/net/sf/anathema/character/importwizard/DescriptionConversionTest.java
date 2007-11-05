package net.sf.anathema.character.importwizard;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;

public class DescriptionConversionTest {

  private CharacterDescriptionConverter converter;

  @Before
  public void createConverter() throws Exception {
    this.converter = new CharacterDescriptionConverter();
  }

  @Test
  public void createsDescription() throws Exception {
    DocumentProvider provider = new DocumentProvider(getClass());
    Document document = provider.readDocument("oldcharacter.ecg"); //$NON-NLS-1$
    Document expecteddocument = provider.readDocument("newbasic.description"); //$NON-NLS-1$
    Document resultdocument = converter.convert(document);
    assertEquals(DocumentUtilities.asString(expecteddocument), DocumentUtilities.asString(resultdocument));
  }

  @Test
  public void createsDescriptionIfPeriphrasisIsMissing() throws Exception {
    Document resultdocument = converter.convert(createEmptyDescriptionDocument());
    Document expecteddocument = createExpectedDocument();
    assertEquals(DocumentUtilities.asString(expecteddocument), DocumentUtilities.asString(resultdocument));
  }

  private Document createExpectedDocument() {
    Element rootElement = DocumentHelper.createElement("model"); //$NON-NLS-1$
    rootElement.addAttribute("bundleVersion", "1.0.0"); //$NON-NLS-1$ //$NON-NLS-2$
    return DocumentHelper.createDocument(rootElement);
  }

  private Document createEmptyDescriptionDocument() {
    Element rootElement = DocumentHelper.createElement("root"); //$NON-NLS-1$
    rootElement.addElement("Description"); //$NON-NLS-1$
    return DocumentHelper.createDocument(rootElement);
  }
}