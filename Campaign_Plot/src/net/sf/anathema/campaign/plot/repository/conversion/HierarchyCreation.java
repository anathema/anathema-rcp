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

public class HierarchyCreation {

  public Document run(Document sourceDocument) throws TransformerException, FileNotFoundException {
    TransformerFactory factory = TransformerFactory.newInstance();
    Transformer transformer = factory.newTransformer(new StreamSource(new FileInputStream("xsl/HierarchyCreation.xsl"))); //$NON-NLS-1$
    DocumentSource source = new DocumentSource(sourceDocument);
    DocumentResult result = new DocumentResult();
    transformer.transform(source, result);
    Document resultdocument = result.getDocument();
    resultdocument.setXMLEncoding("ISO-8859-1"); //$NON-NLS-1$
    return resultdocument;
  }

}
