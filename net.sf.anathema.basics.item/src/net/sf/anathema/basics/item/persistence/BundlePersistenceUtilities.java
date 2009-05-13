package net.sf.anathema.basics.item.persistence;

import java.util.Collections;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class BundlePersistenceUtilities implements IPluginDocumentFactory {

  public static final String ATTRIB_BUNDLE_VERSION = "bundleVersion"; //$NON-NLS-1$
  private final IBundleVersionCollection collection;

  public BundlePersistenceUtilities() {
    this(new BundleVersionCollection());
  }

  public BundlePersistenceUtilities(IBundleVersionCollection collection) {
    this.collection = collection;
  }

  public Document createDocument(String rootElementName, String bundleId) {
    Element root = DocumentHelper.createElement(rootElementName);
    addBundleVersionAttribute(root, bundleId);
    return DocumentHelper.createDocument(root);
  }

  public Map<String, String> getBundleVersionMap(String bundleId) {
    return Collections.singletonMap(ATTRIB_BUNDLE_VERSION, collection.getBundleVersion(bundleId));
  }

  public void addBundleVersionAttribute(Element element, String bundleId) {
    element.addAttribute(ATTRIB_BUNDLE_VERSION, collection.getBundleVersion(bundleId));
  }
}