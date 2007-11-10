package net.sf.anathema.character.description.importwizard;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class DescriptionDocumentObjectMother {
  public static Document createDescriptionWithMissingElements() {
    Element rootElement = DocumentHelper.createElement("root"); //$NON-NLS-1$
    Element description = rootElement.addElement("Description"); //$NON-NLS-1$
    description.addElement("Player"); //$NON-NLS-1$
    return DocumentHelper.createDocument(rootElement);
  }

  public static Document createConvertedDescriptionWithMissingElements() {
    Element rootElement = DocumentHelper.createElement("model"); //$NON-NLS-1$
    rootElement.addElement("Player"); //$NON-NLS-1$
    rootElement.addAttribute("bundleVersion", "1.0.0"); //$NON-NLS-1$ //$NON-NLS-2$
    Document document = DocumentHelper.createDocument(rootElement);
    document.setXMLEncoding("ISO-8859-1"); //$NON-NLS-1$
    return document;
  }
}
