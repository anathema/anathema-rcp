package net.sf.anathema.character.attributes.importwizard;

import java.io.IOException;
import java.util.Map;

import javax.xml.transform.TransformerException;

import net.sf.anathema.basics.importwizard.XSLDocumentConverter;
import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.character.attributes.AttributesPlugin;

import org.dom4j.Document;

public class AttributesConverter {

  private static final String ATTRIBUTE_STYLESHEET = "xsl/AttributeCreation.xsl"; //$NON-NLS-1$

  public static Document convertAttributes(Document document) throws TransformerException, IOException {
    Map<String, String> parameters = new BundlePersistenceUtilities().getBundleVersionMap(AttributesPlugin.ID);
    return new XSLDocumentConverter(AttributesPlugin.ID, ATTRIBUTE_STYLESHEET, parameters).run(document);
  }
}