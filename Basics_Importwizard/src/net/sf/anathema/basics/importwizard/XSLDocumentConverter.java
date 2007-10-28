package net.sf.anathema.basics.importwizard;

import java.io.IOException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import net.sf.anathema.basics.eclipse.resource.ResourceUtils;

import org.dom4j.Document;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;

public class XSLDocumentConverter {

  private final String pluginId;

  public XSLDocumentConverter(String pluginId) {
    this.pluginId = pluginId;
  }

  public Document run(Document sourceDocument, String stylesheet) throws TransformerException, IOException {
    StreamSource sheetStream = new StreamSource(ResourceUtils.getInputStream(pluginId, stylesheet));
    Transformer transformer = TransformerFactory.newInstance().newTransformer(sheetStream);
    DocumentSource source = new DocumentSource(sourceDocument);
    DocumentResult result = new DocumentResult();
    transformer.transform(source, result);
    Document resultdocument = result.getDocument();
    resultdocument.setXMLEncoding("ISO-8859-1"); //$NON-NLS-1$
    return resultdocument;
  }
}