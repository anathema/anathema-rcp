package net.sf.anathema.basics.importexport;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import net.disy.commons.core.creation.IFactory;
import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.eclipse.resource.ResourceUtils;

import org.dom4j.Document;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;

public class XSLDocumentConverter {

  private final Map<String, String> parameters;
  private final IFactory<InputStream, IOException> streamFactory;

  public XSLDocumentConverter(final String pluginId, final String stylesheet, Map<String, String> parameters) {
    this(new IFactory<InputStream, IOException>() {
      @Override
      public InputStream createInstance() throws IOException {
        return ResourceUtils.getInputStream(pluginId, stylesheet);
      }
    }, parameters);
  }

  public XSLDocumentConverter(IFactory<InputStream, IOException> streamFactory, Map<String, String> parameters) {
    this.streamFactory = streamFactory;
    this.parameters = parameters;
  }

  public Document run(Document sourceDocument) throws TransformerException, IOException {
    InputStream inputStream = null;
    try {
      inputStream = streamFactory.createInstance();
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