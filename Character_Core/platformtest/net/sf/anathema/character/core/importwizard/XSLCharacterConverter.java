package net.sf.anathema.character.core.importwizard;

import java.io.IOException;

import javax.xml.transform.TransformerException;

import net.sf.anathema.basics.importwizard.XSLDocumentConverter;
import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;

import org.dom4j.Document;

public class XSLCharacterConverter {

  public static Document convertAttributes(Document document) throws TransformerException, IOException {
    Document result = new XSLDocumentConverter(CharacterCorePlugin.ID).run(document, "xsl/AttributeCreation.xsl");
    BundlePersistenceUtilities.addBundleVersionAttribute(result.getRootElement(), CharacterCorePlugin.ID);
    return result;
  }
}