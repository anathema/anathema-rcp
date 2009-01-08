package net.sf.anathema.character.importwizard;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Map;

import javax.xml.transform.TransformerException;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.importexport.XSLDocumentConverter;
import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;

import org.dom4j.Document;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class XslDocumentConverter implements IDocumentConverter {

  private static final String ATTRIB_BUNDLE = "bundle"; //$NON-NLS-1$
  private static final String ATTRIB_STYLESHEET = "stylesheet"; //$NON-NLS-1$
  private final String pluginId;
  private final URL stylesheet;

  public XslDocumentConverter(IExtensionElement element) {
    this(element.getResourceAttribute(ATTRIB_STYLESHEET), element.getAttribute(ATTRIB_BUNDLE));
  }

  public XslDocumentConverter(URL stylesheet, String pluginId) {
    this.stylesheet = stylesheet;
    this.pluginId = pluginId;
  }

  public Document convert(Document document) throws CoreException, IOException {
    try {
      Map<String, String> parameters = new BundlePersistenceUtilities().getBundleVersionMap(pluginId);
      return new XSLDocumentConverter(stylesheet, parameters).run(document);
    }
    catch (TransformerException e) {
      String message = MessageFormat.format("Failed to convert {0} with {1}", document, stylesheet);
      throw new CoreException(new Status(IStatus.ERROR, pluginId, IStatus.OK, message, e));
    }
  }
}