package net.sf.anathema.charms.xml.data;

import static net.sf.anathema.test.hamcrest.AnathemaMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.xml.TestDocumentReader;

import org.junit.Before;
import org.junit.Test;

public class XmlDurationReader_KeywordTest {

  private CharmDto charmDto;

  @Before
  public void createCosts() throws Exception {
    charmDto = new CharmDto();
  }

  @Test
  public void addsDurationToList() throws Exception {
    readDuration("<duration duration=\"Instant\"/>");
    assertThat(charmDto.durations, is(not(empty())));
  }

  @Test
  public void readsKeywordDuration() throws Exception {
    readDuration("<duration duration=\"Instant\"/>");
    assertThat(charmDto.durations.get(0).keyword, is("Instant"));
  }

  @Test
  public void readsAnyKeyword() throws Exception {
    readDuration("<duration duration=\"Indefinite\"/>");
    assertThat(charmDto.durations.get(0).keyword, is("Indefinite"));
  }

  private void readDuration(String xml) throws Exception {
    TestDocumentReader reader = new TestDocumentReader();
    reader.setXml(xml);
    new XmlDurationReader(reader.readDocument(), charmDto).read();
  }
}