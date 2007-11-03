package net.sf.anathema.character.core.importwizard;

import java.io.InputStream;

import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class DocumentProvider {
  private static final String PATH = "net/sf/anathema/character/core/importwizard/"; //$NON-NLS-1$
  private final Class< ? > clazz;

  public DocumentProvider(Class< ? > clazz) {
    this.clazz = clazz;
  }

  public Document readDocument(String string) throws PersistenceException {
    return DocumentUtilities.read(getStream(string));
  }

  private InputStream getStream(String string) {
    return clazz.getClassLoader().getResourceAsStream(PATH + string);
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