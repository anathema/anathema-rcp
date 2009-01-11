package net.sf.anathema.charms.xml.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.xml.TestDocumentReader;

import org.junit.Before;
import org.junit.Test;

public class XmlDurationReader_UntilTest {

  private CharmDto charmDto;

  @Before
  public void createCosts() throws Exception {
    charmDto = new CharmDto();
  }

  @Test
  public void readsUntilDuration() throws Exception {
    readDuration("<duration event=\"NextAction\"/>");
    assertThat(charmDto.durations.get(0).until, is("NextAction"));
  }

  private void readDuration(String xml) throws Exception {
    TestDocumentReader reader = new TestDocumentReader();
    reader.setXml(xml);
    new XmlDurationReader(reader.readDocument(), charmDto).read();
  }
}