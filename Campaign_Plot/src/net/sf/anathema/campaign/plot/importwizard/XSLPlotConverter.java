package net.sf.anathema.campaign.plot.importwizard;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import net.sf.anathema.basics.eclipse.resource.ResourceUtils;
import net.sf.anathema.campaign.plot.PlotPlugin;

import org.dom4j.Document;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.eclipse.core.runtime.IStatus;

public class XSLPlotConverter {

  public static Document createContent(Document sourceDocument) throws FileNotFoundException, TransformerException {
    try {
      return run(sourceDocument, "xsl/ContentCreation.xsl");
    }
    catch (IOException e) {
      PlotPlugin.getDefaultInstance().log(IStatus.ERROR, "Couldn't convert content", e);
      return null;
    }
  }

  public static Document createHierarchy(Document sourceDocument) throws FileNotFoundException, TransformerException {
    try {
      return run(sourceDocument, "xsl/HierarchyCreation.xsl");
    }
    catch (IOException e) {
      PlotPlugin.getDefaultInstance().log(IStatus.ERROR, "Couldn't convert Hierarchy", e);
      return null;
    }
  }

  private static Document run(Document sourceDocument, String stylesheet) throws TransformerException, IOException {
    TransformerFactory factory = TransformerFactory.newInstance();
    Transformer transformer = factory.newTransformer(new StreamSource(ResourceUtils.getInputStream(
        PlotPlugin.ID,
        stylesheet)));
    DocumentSource source = new DocumentSource(sourceDocument);
    DocumentResult result = new DocumentResult();
    transformer.transform(source, result);
    Document resultdocument = result.getDocument();
    resultdocument.setXMLEncoding("ISO-8859-1"); //$NON-NLS-1$
    return resultdocument;
  }
}
