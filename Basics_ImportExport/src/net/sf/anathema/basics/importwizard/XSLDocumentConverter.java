package net.sf.anathema.basics.importwizard;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

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
  private final Map<String, String> parameters;
  private final String stylesheet;

  public XSLDocumentConverter(String pluginId, String stylesheet, Map<String, String> parameters) {
    this.pluginId = pluginId;
    this.stylesheet = stylesheet;
    this.parameters = parameters;
  }

  public Document run(Document sourceDocument) throws TransformerException, IOException {
    StreamSource sheetStream = new StreamSource(ResourceUtils.getInputStream(pluginId, stylesheet));
    Transformer transformer = TransformerFactory.newInstance().newTransformer(sheetStream);
    DocumentSource source = new DocumentSource(sourceDocument);
    DocumentResult result = new DocumentResult();
    for (Entry<String, String> parameter : parameters.entrySet()) {
      transformer.setParameter(parameter.getKey(), parameter.getValue());
    }
    transformer.transform(source, result);
    Document resultdocument = result.getDocument();
    resultdocument.setXMLEncoding("ISO-8859-1"); //$NON-NLS-1$
    return resultdocument;
  }
}