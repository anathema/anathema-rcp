package net.sf.anathema.basics.importexport;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import net.disy.commons.core.io.IOUtilities;

import org.dom4j.Document;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;

public class XSLDocumentConverter {

  private final Map<String, String> parameters;
  private final URL stylesheet;

  public XSLDocumentConverter(final URL stylesheet, Map<String, String> parameters) {
    this.parameters = parameters;
    this.stylesheet = stylesheet;
  }

  public Document run(Document sourceDocument) throws TransformerException, IOException {
    InputStream inputStream = null;
    try {
      inputStream = stylesheet.openStream();
      StreamSource sheetStream = new StreamSource(inputStream);
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
    finally {
      IOUtilities.close(inputStream);
    }
  }
}