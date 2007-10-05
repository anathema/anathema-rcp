package net.sf.anathema.basics.eclipse.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class ResourceUtils {

  public static URL getResourceUrl(String bundleId, String resourcePath) {
    if (resourcePath == null) {
      return null;
    }
    Bundle bundle = Platform.getBundle(bundleId);
    IPath path = new Path(resourcePath);
    return FileLocator.find(bundle, path, null);
  }

  public static InputStream getInputStream(String bundleId, String resourcePath) throws IOException {
    if (resourcePath == null) {
      return null;
    }
    Bundle bundle = Platform.getBundle(bundleId);
    IPath path = new Path(resourcePath);
    return FileLocator.openStream(bundle, path, false);
  }

}