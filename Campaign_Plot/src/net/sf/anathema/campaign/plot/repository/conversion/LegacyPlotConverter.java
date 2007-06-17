package net.sf.anathema.campaign.plot.repository.conversion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Document;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;

public class LegacyPlotConverter {

  public static Document createContent(Document sourceDocument) throws FileNotFoundException, TransformerException {
    return run(sourceDocument, "xsl/ContentCreation.xsl"); //$NON-NLS-1$
  }
  
  public static Document createHierarchy(Document sourceDocument) throws FileNotFoundException, TransformerException {
    return run(sourceDocument, "xsl/HierarchyCreation.xsl"); //$NON-NLS-1$
  }

  private static Document run(Document sourceDocument, String stylesheet)
      throws TransformerException,
      FileNotFoundException {
    TransformerFactory factory = TransformerFactory.newInstance();
    Transformer transformer = factory.newTransformer(new StreamSource(new FileInputStream(stylesheet)));
    DocumentSource source = new DocumentSource(sourceDocument);
    DocumentResult result = new DocumentResult();
    transformer.transform(source, result);
    Document resultdocument = result.getDocument();
    resultdocument.setXMLEncoding("ISO-8859-1"); //$NON-NLS-1$
    return resultdocument;
  }

}
