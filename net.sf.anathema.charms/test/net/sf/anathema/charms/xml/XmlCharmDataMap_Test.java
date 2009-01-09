package net.sf.anathema.charms.xml;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Properties;

import net.sf.anathema.charms.data.SourceDto;
import net.sf.anathema.charms.data.cost.CostDto;
import net.sf.anathema.charms.data.cost.ResourceDto;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.charms.xml.data.DatedCharmCollection;
import net.sf.anathema.lib.creation.StaticFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

public class XmlCharmDataMap_Test {

  private TestDocumentReader documentReader;
  private XmlCharmDataMap map;
  private final ICharmId KNOWN_ID = new CharmId("Dragon-Blooded.Known", "Archery"); //$NON-NLS-1$ //$NON-NLS-2$
  private final ICharmId UNKNOWN_ID = new CharmId("Dragon-Blooded.Unknown", "Archery"); //$NON-NLS-1$ //$NON-NLS-2$
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
        + " <duration duration=\"Instant\"/>" //$NON-NLS-1$
        + " <charmtype type=\"Supplemental\"/>" //$NON-NLS-1$
        + " <charmAttribute attribute=\"Visible\" visualize=\"true\"/>" //$NON-NLS-1$
        + " <charmAttribute attribute=\"Invisible\"/>" //$NON-NLS-1$
        + " <source source=\"Book\"/>" //$NON-NLS-1$
        + "</charm></charmlist>"); //$NON-NLS-1$
    map = new XmlCharmDataMap();
    sourceProperties = new Properties();
    map.addCharmCollection(new DatedCharmCollection(documentReader));
    map.setSourceProperties(new StaticFactory<Properties, RuntimeException>(sourceProperties));
  }

  @Test
  public void returnsNoDataForUnknownCharm() throws Exception {
    assertThat(map.getData(UNKNOWN_ID), is(nullValue()));
  }

  @Test
  public void hasTypeSupplementalAsLowerCase() throws Exception {
    assertThat(map.getData(KNOWN_ID).type, is("supplemental")); //$NON-NLS-1$
  }

  @Test
  public void hasVisibleKeyword() throws Exception {
    assertThat(map.getData(KNOWN_ID).keywords, JUnitMatchers.hasItem("Visible")); //$NON-NLS-1$
  }

  @Test
  public void hasNotInvisibleKeyword() throws Exception {
    assertThat(map.getData(KNOWN_ID).keywords, not(JUnitMatchers.hasItem("Invisible"))); //$NON-NLS-1$
  }

  @Test
  public void hasOneKeywords() throws Exception {
    assertThat(map.getData(KNOWN_ID).keywords.size(), is(1));
  }

  @Test
  public void hasOneSource() throws Exception {
    assertThat(map.getData(KNOWN_ID).sources.size(), is(1));
  }

  @Test
  public void retainsSourceWithoutSetProperties() throws Exception {
    SourceDto sourceDto = map.getData(KNOWN_ID).sources.get(0);
    assertThat(sourceDto.source, is("Book")); //$NON-NLS-1$
    assertThat(sourceDto.addition, is(nullValue()));
  }

  @Test
  public void replacesSourceNameWithLocalizedName() throws Exception {
    sourceProperties.setProperty("Book", "My Book"); //$NON-NLS-1$ //$NON-NLS-2$
    SourceDto sourceDto = map.getData(KNOWN_ID).sources.get(0);
    assertThat(sourceDto.source, is("My Book")); //$NON-NLS-1$
    assertThat(sourceDto.addition, is(nullValue()));
  }

  @Test
  public void addsSourceAdditionIfSet() throws Exception {
    sourceProperties.setProperty("Book.Dragon-Blooded.Known.Page", "p.234"); //$NON-NLS-1$ //$NON-NLS-2$
    SourceDto sourceDto = map.getData(KNOWN_ID).sources.get(0);
    assertThat(sourceDto.addition, is("p.234")); //$NON-NLS-1$
  }

  @Test
  public void hasCostsForMotes() throws Exception {
    CostDto costDto = map.getData(KNOWN_ID).costs.get(0);
    ResourceDto resourceDto = costDto.resources.get(0);
    assertThat(resourceDto.type, is("motes")); //$NON-NLS-1$
  }

  @Test
  public void hasAlternativeCostsForWillpower() throws Exception {
    CostDto costDto = map.getData(KNOWN_ID).costs.get(1);
    ResourceDto resourceDto = costDto.resources.get(0);
    assertThat(resourceDto.type, is("willpower")); //$NON-NLS-1$
  }
}