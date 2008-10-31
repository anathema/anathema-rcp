package net.sf.anathema.character.experience.importwizard;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ExperienceDocumentObjectMother {

  public static Document createExperienceDocument() {
    Element element = DocumentHelper.createElement("Character"); //$NON-NLS-1$
    Element stats = element.addElement("Statistics"); //$NON-NLS-1$    
    stats.addAttribute("experienced", "true"); //$NON-NLS-1$ //$NON-NLS-2$
    return DocumentHelper.createDocument(element);
  }

  public static Document createConvertedExperienceDocument() {
    Element element = DocumentHelper.createElement("model"); //$NON-NLS-1$
    element.addAttribute("bundleVersion", "1.0.0"); //$NON-NLS-1$ //$NON-NLS-2$
    element.addAttribute("experienced", "true"); //$NON-NLS-1$ //$NON-NLS-2$
    return DocumentHelper.createDocument(element);
  }
}