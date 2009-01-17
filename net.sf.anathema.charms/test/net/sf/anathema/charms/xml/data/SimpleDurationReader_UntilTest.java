package net.sf.anathema.charms.xml.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.duration.DurationDto;
import net.sf.anathema.charms.xml.TestDocumentReader;

import org.junit.Test;

public class SimpleDurationReader_UntilTest {

  @Test
  public void readsUntilDuration() throws Exception {
    DurationDto duration = readDuration("<duration event=\"NextAction\"/>"); //$NON-NLS-1$
    assertThat(duration.additions.get(0).until, is("NextAction")); //$NON-NLS-1$
  }

  private DurationDto readDuration(String xml) throws Exception {
    TestDocumentReader reader = new TestDocumentReader();
    reader.setXml(xml);
    return new SimpleDurationReader(reader.readDocument()).read();
  }
}