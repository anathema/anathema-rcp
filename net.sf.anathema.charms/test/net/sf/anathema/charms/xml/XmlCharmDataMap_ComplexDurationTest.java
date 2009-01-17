package net.sf.anathema.charms.xml;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Properties;

import net.sf.anathema.charms.data.duration.DurationDto;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.charms.xml.data.DatedCharmCollection;
import net.sf.anathema.lib.creation.StaticFactory;

import org.junit.Before;
import org.junit.Test;

public class XmlCharmDataMap_ComplexDurationTest {

  private TestDocumentReader documentReader;
  private XmlCharmDataMap map;
  private final ICharmId KNOWN_ID = new CharmId("Dragon-Blooded.Known", "Archery"); //$NON-NLS-1$ //$NON-NLS-2$
  private Properties sourceProperties;

  @Before
  public void createCharmDataMap() {
    documentReader = new TestDocumentReader();
    documentReader.setXml("<charmlist>" //$NON-NLS-1$
        + "<charm id=\"Dragon-Blooded.Known\" group=\"Archery\">" //$NON-NLS-1$
        + " <cost>" //$NON-NLS-1$
        + "   <essence cost=\"2\"/>" //$NON-NLS-1$
        + " </cost>" //$NON-NLS-1$
        + " <cost>" //$NON-NLS-1$
        + "   <willpower cost=\"1\"/>" //$NON-NLS-1$
        + " </cost>" //$NON-NLS-1$
        + "<complexDuration>"
        + "        <minimum>"
        + "            <duration amount=\"1\" unit=\"task\"/>\r\n"
        + "            <duration amount=\"3\" unit=\"month\"/>\r\n"
        + "        </minimum>"
        + "</complexDuration>"
        + " <charmtype type=\"Supplemental\"/>" //$NON-NLS-1$
        + " <charmAttribute attribute=\"Visible\" visualize=\"true\"/>" //$NON-NLS-1$
        + " <source source=\"Book\"/>" //$NON-NLS-1$
        + "</charm></charmlist>"); //$NON-NLS-1$
    map = new XmlCharmDataMap();
    sourceProperties = new Properties();
    map.addCharmCollection(new DatedCharmCollection(documentReader));
    map.setSourceProperties(new StaticFactory<Properties, RuntimeException>(sourceProperties));
  }

  @Test
  public void hasInstantDuration() throws Exception {
    DurationDto durationDto = map.getData(KNOWN_ID).durations.get(0);
    assertThat(durationDto.minimums.size(), is(2));
  }
}