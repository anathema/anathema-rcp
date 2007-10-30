package net.sf.anathema.basics.item.persistence;

import java.util.Collections;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class BundlePersistenceUtilities {

  private static final String HEADER_BUNDLE_VERSION = "Bundle-Version"; //$NON-NLS-1$
  public static final String ATTRIB_BUNDLE_VERSION = "bundleVersion"; //$NON-NLS-1$

  public static Document createVersionedDocument(String rootElementName, String bundleId) {
    Element root = DocumentHelper.createElement(rootElementName);
    addBundleVersionAttribute(root, bundleId);
    return DocumentHelper.createDocument(root);
  }

  public static Map<String, String> getBundleVersionMap(String bundleId) {
    return Collections.singletonMap(ATTRIB_BUNDLE_VERSION, getBundleVersion(bundleId));
  }

  public static void addBundleVersionAttribute(Element element, String bundleId) {
    String version = getBundleVersion(bundleId);
    element.addAttribute(ATTRIB_BUNDLE_VERSION, version);
  }

  private static String getBundleVersion(String bundleId) {
    Bundle bundle = Platform.getBundle(bundleId);
    return (String) bundle.getHeaders().get(HEADER_BUNDLE_VERSION);
  }

}