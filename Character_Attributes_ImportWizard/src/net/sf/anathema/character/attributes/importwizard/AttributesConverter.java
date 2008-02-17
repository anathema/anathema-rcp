package net.sf.anathema.character.attributes.importwizard;

import java.io.IOException;
import java.util.Map;

import javax.xml.transform.TransformerException;

import net.sf.anathema.basics.importexport.XSLDocumentConverter;
import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.character.attributes.AttributesPlugin;
import net.sf.anathema.character.importwizard.IDocumentConverter;

import org.dom4j.Document;
import org.eclipse.core.runtime.CoreException;

public class AttributesConverter implements IDocumentConverter {

  private static final String ATTRIBUTE_STYLESHEET = "xsl/AttributeCreation.xsl"; //$NON-NLS-1$

  public Document convert(Document document) throws CoreException, IOException {
    try {
      Map<String, String> parameters = new BundlePersistenceUtilities().getBundleVersionMap(AttributesPlugin.ID);
      return new XSLDocumentConverter(AttributesPlugin.ID, ATTRIBUTE_STYLESHEET, parameters).run(document);
    }
    catch (TransformerException e) {
      throw new CoreException(AttributesPlugin.getDefaultInstance().createErrorStatus(
          Messages.AttributesImporter_ErrorMessage,
          e));
    }
  }
}