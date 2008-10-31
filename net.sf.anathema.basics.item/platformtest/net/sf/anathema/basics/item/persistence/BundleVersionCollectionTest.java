package net.sf.anathema.basics.item.persistence;

import static org.junit.Assert.*;

import org.eclipse.core.runtime.Platform;
import org.junit.Test;

public class BundleVersionCollectionTest {

  private static final String BUNDLE_ID = "net.sf.anathema.basics.item"; //$NON-NLS-1$

  @Test
  public void returnsVersionForPlugin() throws Exception {
    Object version = Platform.getBundle(BUNDLE_ID).getHeaders().get("Bundle-Version"); //$NON-NLS-1$
    assertEquals(version, new BundleVersionCollection().getBundleVersion(BUNDLE_ID));
  }

  @Test
  public void returnsNullForMissingPlugin() throws Exception {
    assertNull(new BundleVersionCollection().getBundleVersion("xyz")); //$NON-NLS-1$
  }
}
