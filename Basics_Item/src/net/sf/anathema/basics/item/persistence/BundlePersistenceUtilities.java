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
    Bundle bundle = Platform.getBundle(bundleId);
    root.addAttribute(ATTRIB_BUNDLE_VERSION, (String) bundle.getHeaders().get(HEADER_BUNDLE_VERSION));
    Document document = DocumentHelper.createDocument(root);
    return document;
  }
}