package net.sf.anathema.charms.xml.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.duration.DurationDto;
import net.sf.anathema.charms.xml.TestDocumentReader;

import org.junit.Before;
import org.junit.Test;

public class ComplexDurationReader_MinimumTest {

  private DurationDto duration;

  @Before
  public void createDuration() throws Exception {
    duration = readDuration("<complexDuration>" //$NON-NLS-1$
        + "<minimum>" //$NON-NLS-1$
        + "<duration event=\"PerformanceEnds\"/>" //$NON-NLS-1$
        + "<duration trait=\"Essence\" unit=\"hour\"/>" //$NON-NLS-1$
        + "</minimum>" //$NON-NLS-1$
        + "</complexDuration>"); //$NON-NLS-1$
  }

  @Test
  public void readsUnit() throws Exception {
    assertThat(duration.minimums.size(), is(2));
  }

  private DurationDto readDuration(String xml) throws Exception {
    TestDocumentReader reader = new TestDocumentReader();
    reader.setXml(xml);
    return new ComplexDurationReader(reader.readDocument()).read();
  }
}