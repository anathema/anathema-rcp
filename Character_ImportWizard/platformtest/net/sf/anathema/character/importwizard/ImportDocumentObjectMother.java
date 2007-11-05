package net.sf.anathema.character.importwizard;

import java.io.InputStream;

import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ImportDocumentObjectMother {
  private static final String PATH = "net/sf/anathema/character/importwizard/"; //$NON-NLS-1$
  private final Class< ? > clazz;

  public ImportDocumentObjectMother(Class< ? > clazz) {
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

  public static Document createEmptyDescriptionDocument() {
    Element rootElement = DocumentHelper.createElement("root"); //$NON-NLS-1$
    rootElement.addElement("Description"); //$NON-NLS-1$
    return DocumentHelper.createDocument(rootElement);
  }

  public static Document createEmptyVersionedModelDocument() {
    Element rootElement = DocumentHelper.createElement("model"); //$NON-NLS-1$
    rootElement.addAttribute("bundleVersion", "1.0.0"); //$NON-NLS-1$ //$NON-NLS-2$
    Document document = DocumentHelper.createDocument(rootElement);
    document.setXMLEncoding("ISO-8859-1"); //$NON-NLS-1$
    return document;
  }
}