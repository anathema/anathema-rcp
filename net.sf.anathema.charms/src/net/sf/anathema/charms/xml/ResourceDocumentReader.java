package net.sf.anathema.charms.xml;

import java.io.InputStream;
import java.net.URL;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.eclipse.resource.ResourceUtils;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;
import org.eclipse.core.runtime.IContributor;

public class ResourceDocumentReader implements IDocumentReader {

  private final IContributor contributor;
  private final String resourcePath;

  public ResourceDocumentReader(IContributor contributor, String resourcePath) {
    this.contributor = contributor;
    this.resourcePath = resourcePath;
  }

  public Element readDocument() throws Exception {
    URL resourceUrl = ResourceUtils.getResourceUrl(contributor.getName(), resourcePath);
    InputStream inputStream = null;
    try {
      inputStream = resourceUrl.openStream();
      return DocumentUtilities.read(inputStream).getDocument().getRootElement();
    }
    finally {
      IOUtilities.close(inputStream);
    }
  }
}
