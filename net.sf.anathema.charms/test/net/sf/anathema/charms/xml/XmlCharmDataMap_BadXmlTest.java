package net.sf.anathema.charms.xml;

import net.sf.anathema.charms.xml.data.DatedCharmCollection;

import org.junit.Before;
import org.junit.Test;

public class XmlCharmDataMap_BadXmlTest {

  private DatedCharmCollection charmCollection;

  @Before
  public void createCharmDataMap() {
    TestDocumentReader documentReader = new TestDocumentReader();
    documentReader.setXml("<charmlist>" //$NON-NLS-1$
        + "<charm id=\"Dragon-Blooded.Known\" group=\"Archery\">" //$NON-NLS-1$
        + "<FEHLER"
        + "</charmlist>"); //$NON-NLS-1$
    charmCollection = new DatedCharmCollection(documentReader);
  }

  @Test(expected = RuntimeException.class)
  public void throwsRuntimeExceptionWithBadXml() throws Exception {
    charmCollection.iterator();
  }
}