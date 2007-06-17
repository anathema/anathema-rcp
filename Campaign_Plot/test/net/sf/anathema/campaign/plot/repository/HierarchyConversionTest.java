package net.sf.anathema.campaign.plot.repository;

import java.io.InputStream;

import net.sf.anathema.campaign.plot.repository.conversion.LegacyPlotConverter;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.junit.Assert;
import org.junit.Test;

public class HierarchyConversionTest {
  private static final String PATH = "net/sf/anathema/campaign/plot/repository/"; //$NON-NLS-1$

  @Test
  public void createsHierarchy() throws Exception {
    Document document = readDocument("oldmain.srs"); //$NON-NLS-1$
    Document expecteddocument = readDocument("newhierarchy.xml"); //$NON-NLS-1$
    Document resultdocument = LegacyPlotConverter.createHierarchy(document);
    Assert.assertEquals(expecteddocument.asXML(), resultdocument.asXML());
  }

  @Test
  public void cleansUpContent() throws Exception {
    Document document = readDocument("oldmain.srs"); //$NON-NLS-1$
    Document expecteddocument = readDocument("newmain.srs"); //$NON-NLS-1$
    Document resultdocument = LegacyPlotConverter.createContent(document);
    Assert.assertEquals(expecteddocument.asXML(), resultdocument.asXML());   
  }
  
  private Document readDocument(String string) throws PersistenceException {
    return DocumentUtilities.read(getStream(string));
  }

  private InputStream getStream(String string) {
    return getClass().getClassLoader().getResourceAsStream(PATH + string);
  }
}
