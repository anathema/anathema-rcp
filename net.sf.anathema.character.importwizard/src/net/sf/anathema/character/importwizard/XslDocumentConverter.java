package net.sf.anathema.character.importwizard;

import java.io.IOException;
import java.util.Map;

import javax.xml.transform.TransformerException;

import net.sf.anathema.basics.importexport.XSLDocumentConverter;
import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;

import org.dom4j.Document;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class XslDocumentConverter implements IDocumentConverter {

  private final String stylesheet;
  private final String pluginId;
  private final String errormessage;

  public XslDocumentConverter(String stylesheet, String pluginId, String errormessage) {
    this.stylesheet = stylesheet;
    this.pluginId = pluginId;
    this.errormessage = errormessage;
  }

  public Document convert(Document document) throws CoreException, IOException {
    try {
      Map<String, String> parameters = new BundlePersistenceUtilities().getBundleVersionMap(pluginId);
      return new XSLDocumentConverter(pluginId, stylesheet, parameters).run(document);
    }
    catch (TransformerException e) {
      throw new CoreException(new Status(IStatus.ERROR, pluginId, IStatus.OK, errormessage, e));
    }
  }
}