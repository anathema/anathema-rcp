package net.sf.anathema.charms.xml.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.duration.DurationDto;
import net.sf.anathema.charms.xml.TestDocumentReader;

import org.junit.Test;

public class SimpleDurationReader_KeywordTest {

  @Test
  public void readsKeywordDuration() throws Exception {
    DurationDto duration = readDuration("<duration duration=\"Instant\"/>"); //$NON-NLS-1$
    assertThat(duration.keyword, is("Instant")); //$NON-NLS-1$
  }

  @Test
  public void readsAnyKeyword() throws Exception {
    DurationDto duration = readDuration("<duration duration=\"Indefinite\"/>"); //$NON-NLS-1$
    assertThat(duration.keyword, is("Indefinite")); //$NON-NLS-1$
  }

  private DurationDto readDuration(String xml) throws Exception {
    TestDocumentReader reader = new TestDocumentReader();
    reader.setXml(xml);
    return new SimpleDurationReader(reader.readDocument()).read();
  }
}