package net.sf.anathema.character.core.importwizard;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.xml.transform.TransformerException;

import net.sf.anathema.basics.importwizard.XSLDocumentConverter;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;

import org.dom4j.Document;

public class XSLCharacterConverter {

  private static final String ATTRIBUTE_STYLESHEET = "xsl/AttributeCreation.xsl"; //$NON-NLS-1$

  public static Document convertAttributes(Document document) throws TransformerException, IOException {
    Map<String, String> parameters = Collections.singletonMap("bundleVersion", "1.0.0");
    Document result = new XSLDocumentConverter(CharacterCorePlugin.ID, ATTRIBUTE_STYLESHEET, parameters).run(document);
    return result;
  }
}