package net.sf.anathema.basics.item.persistence;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class BundleVersionCollection implements IBundleVersionCollection {
  private static final String HEADER_BUNDLE_VERSION = "Bundle-Version"; //$NON-NLS-1$

  @Override
  public String getBundleVersion(String bundleId) {
    Bundle bundle = Platform.getBundle(bundleId);
    if (bundle == null) {
      return null;
    }
    return (String) bundle.getHeaders().get(HEADER_BUNDLE_VERSION);
  }
}