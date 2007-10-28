package net.sf.anathema.basics.item.persistence;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class BundlePersistenceUtilities {

  private static final String HEADER_BUNDLE_VERSION = "Bundle-Version"; //$NON-NLS-1$
  private static final String ATTRIB_BUNDLE_VERSION = "bundleVersion"; //$NON-NLS-1$

  public static Document createVersionedDocument(String rootElementName, String bundleId) {
    Element root = DocumentHelper.createElement(rootElementName);
    addBundleVersionAttribute(root, bundleId);
    Document document = DocumentHelper.createDocument(root);
    return document;
  }

  public static void addBundleVersionAttribute(Element element, String bundleId) {
    Bundle bundle = Platform.getBundle(bundleId);
    element.addAttribute(ATTRIB_BUNDLE_VERSION, (String) bundle.getHeaders().get(HEADER_BUNDLE_VERSION));
  }
}