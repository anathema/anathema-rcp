package net.sf.anathema.basics.item.persistence;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class UnversionedDocumentFactory implements IPluginDocumentFactory {

  @Override
  public Document createDocument(String rootElementName, String bundleId) {
    Element rootElement = DocumentHelper.createElement(rootElementName);
    return DocumentHelper.createDocument(rootElement);
  }
}