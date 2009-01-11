package net.sf.anathema.charms.xml.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.xml.TestDocumentReader;

import org.junit.Before;
import org.junit.Test;

public class XmlDurationReader_AndTest {

  private CharmDto charmDto;

  @Before
  public void createCosts() throws Exception {
    charmDto = new CharmDto();
  }

  @Test
  public void readsUnit() throws Exception {
    readDuration("<duration event=\"PerformanceEnds\" trait=\"Essence\" unit=\"hour\"/>");
    assertThat(charmDto.durations.get(0).amount.unit, is("hour"));
  }

  @Test
  public void readsTrait() throws Exception {
    readDuration("<duration event=\"PerformanceEnds\" trait=\"Essence\" unit=\"hour\"/>");
    assertThat(charmDto.durations.get(0).amount.trait, is("Essence"));
  }

  @Test
  public void readsEvent() throws Exception {
    readDuration("<duration event=\"PerformanceEnds\" trait=\"Essence\" unit=\"hour\"/>");
    assertThat(charmDto.durations.get(0).until, is("PerformanceEnds"));
  }

  private void readDuration(String xml) throws Exception {
    TestDocumentReader reader = new TestDocumentReader();
    reader.setXml(xml);
    new XmlDurationReader(reader.readDocument(), charmDto).read();
  }
}