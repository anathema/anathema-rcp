package net.sf.anathema.character.core.importwizard;

import java.io.IOException;
import java.util.Map;

import javax.xml.transform.TransformerException;

import net.sf.anathema.basics.importwizard.XSLDocumentConverter;
import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;

import org.dom4j.Document;

public class XSLCharacterConverter {

  private static final String ATTRIBUTE_STYLESHEET = "xsl/AttributeCreation.xsl"; //$NON-NLS-1$
  private static final String DESCRIPTION_STYLESHEET = "xsl/DescriptionCreation.xsl"; //$NON-NLS-1$

  public static Document convertAttributes(Document document) throws TransformerException, IOException {
    return runConversion(document, ATTRIBUTE_STYLESHEET);
  }

  private static Document runConversion(Document document, String attributeStylesheet)
      throws TransformerException,
      IOException {
    Map<String, String> parameters = BundlePersistenceUtilities.getBundleVersionMap(CharacterCorePlugin.ID);
    return new XSLDocumentConverter(CharacterCorePlugin.ID, attributeStylesheet, parameters).run(document);
  }

  public static Document convertDescription(Document document) throws TransformerException, IOException {
    return runConversion(document, DESCRIPTION_STYLESHEET);
  }
}