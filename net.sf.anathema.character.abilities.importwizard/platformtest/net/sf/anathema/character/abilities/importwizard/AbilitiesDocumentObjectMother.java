package net.sf.anathema.character.abilities.importwizard;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class AbilitiesDocumentObjectMother {

  public static Document createConvertedAttributesDocument() {
    Element rootElement = DocumentHelper.createElement("model"); //$NON-NLS-1$
    Element attribute = rootElement.addElement("trait"); //$NON-NLS-1$
    attribute.addAttribute("id", "Ability"); //$NON-NLS-1$ //$NON-NLS-2$
    attribute.addAttribute("value", "5"); //$NON-NLS-1$ //$NON-NLS-2$
    rootElement.addAttribute("bundleVersion", "1.0.0"); //$NON-NLS-1$ //$NON-NLS-2$
    Document document = DocumentHelper.createDocument(rootElement);
    document.setXMLEncoding("ISO-8859-1"); //$NON-NLS-1$
    return document;
  }

  public static Document createAttributesDocument() {
    Element rootElement = DocumentHelper.createElement("ExaltedCharacter"); //$NON-NLS-1$
    Element stats = rootElement.addElement("Statistics"); //$NON-NLS-1$
    Element abilities = stats.addElement("Abilities"); //$NON-NLS-1$
    Element ability = abilities.addElement("Ability"); //$NON-NLS-1$
    ability.addAttribute("value", "5"); //$NON-NLS-1$ //$NON-NLS-2$
    return DocumentHelper.createDocument(rootElement);
  }
}