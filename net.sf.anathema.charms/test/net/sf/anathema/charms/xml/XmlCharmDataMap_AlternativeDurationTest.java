package net.sf.anathema.charms.xml;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Properties;

import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.duration.DurationDto;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.charms.xml.data.DatedCharmCollection;
import net.sf.anathema.lib.creation.StaticFactory;

import org.junit.Before;
import org.junit.Test;

public class XmlCharmDataMap_AlternativeDurationTest {

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
        + "<duration trait=\"Essence\" unit=\"action\"/>" //$NON-NLS-1$
        + "<duration trait=\"Essence\" unit=\"longtick\"/>" //$NON-NLS-1$
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
  public void hasTwoDuration() throws Exception {
    CharmDto charmData = map.getData(KNOWN_ID);
    List<DurationDto> durationDtos = charmData.durations;
    assertThat(durationDtos.size(), is(2));
  }
}