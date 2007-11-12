package net.sf.anathema.basics.item.persistence;

import static org.junit.Assert.assertEquals;

import java.util.Dictionary;

import net.sf.anathema.basics.item.plugin.IBasicItemPluginConstants;

import org.dom4j.Document;
import org.eclipse.core.runtime.Platform;
import org.junit.Test;

public class BundlePersistenceUtilitiesTest {

  @Test
  public void documentRootContainsVersion() throws Exception {
    Document doc = new BundlePersistenceUtilities().createVersionedDocument("model", "net.sf.anathema.basics.item"); //$NON-NLS-1$ //$NON-NLS-2$ 
    String version = doc.getRootElement().attributeValue("bundleVersion"); //$NON-NLS-1$
    Dictionary< ? , ? > headers = Platform.getBundle(IBasicItemPluginConstants.PLUGIN_ID).getHeaders();
    assertEquals(headers.get("Bundle-Version"), version); //$NON-NLS-1$
  }
}