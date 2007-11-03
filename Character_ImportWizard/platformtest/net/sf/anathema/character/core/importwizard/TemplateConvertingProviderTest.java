package net.sf.anathema.character.core.importwizard;

import static org.junit.Assert.*;
import net.sf.anathema.character.importwizard.TemplateConvertingProvider;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

public class TemplateConvertingProviderTest {

  @Test
  public void convertsTemplateName() throws Exception {
    Document document = getDocumentForTemplate("Solar", "default"); //$NON-NLS-1$ //$NON-NLS-2$
    TemplateConvertingProvider provider = new TemplateConvertingProvider(document);
    assertEquals("net.sf.anathema.character.type.solar", provider.get()); //$NON-NLS-1$
  }

  @Test
  public void convertsDifferentTemplateName() throws Exception {
    Document document = getDocumentForTemplate("Sidereal", "default"); //$NON-NLS-1$ //$NON-NLS-2$
    TemplateConvertingProvider provider = new TemplateConvertingProvider(document);
    assertEquals("net.sf.anathema.charactertype.defaultsidereal", provider.get()); //$NON-NLS-1$
  }

  @Test
  public void returnsNullForUnknownTemplate() throws Exception {
    Document document = getDocumentForTemplate("Sidereal", "HasänTum"); //$NON-NLS-1$ //$NON-NLS-2$
    TemplateConvertingProvider provider = new TemplateConvertingProvider(document);
    assertNull(provider.get());
  }

  @Test
  public void returnsNullForUnknownType() throws Exception {
    Document document = getDocumentForTemplate("Hasä", "default"); //$NON-NLS-1$ //$NON-NLS-2$
    TemplateConvertingProvider provider = new TemplateConvertingProvider(document);
    assertNull(provider.get());
  }

  private Document getDocumentForTemplate(String type, String subtype) {
    Document document = DocumentHelper.createDocument(DocumentHelper.createElement("name")); //$NON-NLS-1$
    Element stats = document.getRootElement().addElement("Statistics"); //$NON-NLS-1$
    Element typeElement = stats.addElement("CharacterType"); //$NON-NLS-1$
    typeElement.addText(type);
    typeElement.addAttribute("subtype", subtype); //$NON-NLS-1$
    return document;
  }
}