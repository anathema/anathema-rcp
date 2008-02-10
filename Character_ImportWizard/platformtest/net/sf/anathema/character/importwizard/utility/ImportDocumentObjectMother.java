package net.sf.anathema.character.importwizard.utility;

import java.io.InputStream;

import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ImportDocumentObjectMother {

  private static InputStream getStream(Class< ? > clazz, String string) {
    return clazz.getClassLoader().getResourceAsStream(string);
  }

  public static Document getDocumentFromFile(Class< ? > clazz, String path, String filename)
      throws PersistenceException {
    return DocumentUtilities.read(getStream(clazz, path + filename));
  }

  public static Document createDocumentForTemplate(String type, String subtype) {
    Document document = DocumentHelper.createDocument(DocumentHelper.createElement("name")); //$NON-NLS-1$
    Element stats = document.getRootElement().addElement("Statistics"); //$NON-NLS-1$
    Element typeElement = stats.addElement("CharacterType"); //$NON-NLS-1$
    typeElement.addText(type);
    typeElement.addAttribute("subtype", subtype); //$NON-NLS-1$
    return document;
  }
}