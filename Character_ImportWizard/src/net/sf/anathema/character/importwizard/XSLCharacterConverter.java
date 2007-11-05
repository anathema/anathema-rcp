package net.sf.anathema.character.importwizard;

import java.io.IOException;
import java.util.Map;

import javax.xml.transform.TransformerException;

import net.sf.anathema.basics.importwizard.XSLDocumentConverter;
import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.character.importwizard.plugin.CharacterImportWizardPluginConstants;

import org.dom4j.Document;

public class XSLCharacterConverter {

  private static final String ATTRIBUTE_STYLESHEET = "xsl/AttributeCreation.xsl"; //$NON-NLS-1$

  public static Document convertAttributes(Document document) throws TransformerException, IOException {
    // TODO Get constant character_core.id
    Map<String, String> parameters = BundlePersistenceUtilities.getBundleVersionMap("net.sf.anathema.character.core");
    return new XSLDocumentConverter(CharacterImportWizardPluginConstants.PLUGIN_ID, ATTRIBUTE_STYLESHEET, parameters).run(document);
  }
}