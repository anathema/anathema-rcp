package net.sf.anathema.character.attributes.importwizard;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class AttributesDocumentObjectMother {

  public static Document createConvertedAttributesDocument() {
    Element rootElement = DocumentHelper.createElement("model"); //$NON-NLS-1$
    Element attribute = rootElement.addElement("trait"); //$NON-NLS-1$
    attribute.addAttribute("id", "Attribut"); //$NON-NLS-1$ //$NON-NLS-2$
    attribute.addAttribute("value", "5"); //$NON-NLS-1$ //$NON-NLS-2$
    rootElement.addAttribute("bundleVersion", "1.0.0"); //$NON-NLS-1$ //$NON-NLS-2$
    Document document = DocumentHelper.createDocument(rootElement);
    document.setXMLEncoding("ISO-8859-1"); //$NON-NLS-1$
    return document;
  }

  public static Document createAttributesDocument() {
    Element rootElement = DocumentHelper.createElement("ExaltedCharacter"); //$NON-NLS-1$
    Element stats = rootElement.addElement("Statistics"); //$NON-NLS-1$
    Element attributes = stats.addElement("Attributes"); //$NON-NLS-1$
    Element physical = attributes.addElement("Physical"); //$NON-NLS-1$
    Element attribute = physical.addElement("Attribut"); //$NON-NLS-1$
    attribute.addAttribute("value", "5"); //$NON-NLS-1$ //$NON-NLS-2$
    return DocumentHelper.createDocument(rootElement);
  }
}