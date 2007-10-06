package net.sf.anathema.campaign.plot.importwizard;

import java.io.IOException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import net.sf.anathema.basics.eclipse.resource.ResourceUtils;
import net.sf.anathema.campaign.plot.PlotPlugin;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.eclipse.core.runtime.IStatus;

public class XSLPlotConverter {

  public static Document createContent(Document sourceDocument) throws PersistenceException {
    try {
      Document document = run(sourceDocument, "xsl/ContentCreation.xsl"); //$NON-NLS-1$
      Element name = document.getRootElement().element("Name");
      String text = name.getText();
      name.clearContent();
      name.addCDATA(text);
      return document;

    }
    catch (IOException e) {
      PlotPlugin.getDefaultInstance().log(IStatus.ERROR, "Failed to convert content.", e);
      throw new PersistenceException(e);
    }
    catch (TransformerException e) {
      PlotPlugin.getDefaultInstance().log(IStatus.ERROR, "Failed to convert content.", e);
      throw new PersistenceException(e);
    }
  }

  public static Document createHierarchy(Document sourceDocument) throws PersistenceException {
    try {
      return run(sourceDocument, "xsl/HierarchyCreation.xsl");
    }
    catch (IOException e) {
      PlotPlugin.getDefaultInstance().log(IStatus.ERROR, "Failed to convert content.", e);
      throw new PersistenceException(e);
    }
    catch (TransformerException e) {
      PlotPlugin.getDefaultInstance().log(IStatus.ERROR, "Failed to convert content.", e);
      throw new PersistenceException(e);
    }
  }

  private static Document run(Document sourceDocument, String stylesheet) throws TransformerException, IOException {
    StreamSource sheetStream = new StreamSource(ResourceUtils.getInputStream(PlotPlugin.ID, stylesheet));
    Transformer transformer = TransformerFactory.newInstance().newTransformer(sheetStream);
    DocumentSource source = new DocumentSource(sourceDocument);
    DocumentResult result = new DocumentResult();
    transformer.transform(source, result);
    Document resultdocument = result.getDocument();
    resultdocument.setXMLEncoding("ISO-8859-1"); //$NON-NLS-1$
    return resultdocument;
  }
}
