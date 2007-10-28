package net.sf.anathema.campaign.plot.importwizard;

import java.io.IOException;

import javax.xml.transform.TransformerException;

import net.sf.anathema.basics.importwizard.XSLDocumentConverter;
import net.sf.anathema.campaign.plot.PlotPlugin;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Document;
import org.dom4j.Element;
import org.eclipse.core.runtime.IStatus;

public class XSLPlotConverter {

  private static final String HIERARCHY_STYLESHEET = "xsl/HierarchyCreation.xsl"; //$NON-NLS-1$
  private static final String CONTENT_STYLESHEET = "xsl/ContentCreation.xsl"; //$NON-NLS-1$
  private static final String TAG_NAME = "Name"; //$NON-NLS-1$

  public static Document createContent(Document sourceDocument) throws PersistenceException {
    try {
      Document document = new XSLDocumentConverter(PlotPlugin.ID).run(sourceDocument, CONTENT_STYLESHEET);
      Element name = document.getRootElement().element(TAG_NAME);
      String text = name.getText();
      name.clearContent();
      name.addCDATA(text);
      return document;

    }
    catch (IOException e) {
      PlotPlugin.getDefaultInstance().log(IStatus.ERROR, Messages.XSLPlotConverter_FailedToConvertConvert, e);
      throw new PersistenceException(e);
    }
    catch (TransformerException e) {
      PlotPlugin.getDefaultInstance().log(IStatus.ERROR, Messages.XSLPlotConverter_FailedToConvertConvert, e);
      throw new PersistenceException(e);
    }
  }

  public static Document createHierarchy(Document sourceDocument) throws PersistenceException {
    try {
      return new XSLDocumentConverter(PlotPlugin.ID).run(sourceDocument, HIERARCHY_STYLESHEET);
    }
    catch (IOException e) {
      PlotPlugin.getDefaultInstance().log(IStatus.ERROR, Messages.XSLPlotConverter_FailedToConvertHierarchy, e);
      throw new PersistenceException(e);
    }
    catch (TransformerException e) {
      PlotPlugin.getDefaultInstance().log(IStatus.ERROR, Messages.XSLPlotConverter_FailedToConvertHierarchy, e);
      throw new PersistenceException(e);
    }
  }
}
