package net.sf.anathema.basics.item.persistence;

import org.dom4j.Document;

public interface IPluginDocumentFactory {

  public Document createDocument(String rootElementName, String bundleId);

}