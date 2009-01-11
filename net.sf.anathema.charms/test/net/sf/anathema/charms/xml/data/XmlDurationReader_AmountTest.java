package net.sf.anathema.charms.xml.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.xml.TestDocumentReader;

import org.junit.Before;
import org.junit.Test;

public class XmlDurationReader_AmountTest {

  private CharmDto charmDto;

  @Before
  public void createCosts() throws Exception {
    charmDto = new CharmDto();
  }

  @Test
  public void readsUnit() throws Exception {
    readDuration("<duration amount=\"1\" unit=\"scene\"/>");
    assertThat(charmDto.durations.get(0).amount.unit, is("scene"));
  }

  @Test
  public void readsAmount() throws Exception {
    readDuration("<duration amount=\"1\" unit=\"scene\"/>");
    assertThat(charmDto.durations.get(0).amount.value, is("1"));
  }

  @Test
  public void readsTrait() throws Exception {
    readDuration("<duration trait=\"Essence\" unit=\"scene\"/>");
    assertThat(charmDto.durations.get(0).amount.trait, is("Essence"));
  }

  @Test
  public void readsTextualAmounts() throws Exception {
    readDuration("<duration amount=\"Successes\" unit=\"scene\"/>");
    assertThat(charmDto.durations.get(0).amount.value, is("Successes"));
  }

  @Test
  public void readsMultiplier() throws Exception {
    readDuration("<duration trait=\"Essence\" multiplier=\"5\" unit=\"scene\"/>");
    assertThat(charmDto.durations.get(0).amount.multiplier, is(5));
  }

  private void readDuration(String xml) throws Exception {
    TestDocumentReader reader = new TestDocumentReader();
    reader.setXml(xml);
    new XmlDurationReader(reader.readDocument(), charmDto).read();
  }
}