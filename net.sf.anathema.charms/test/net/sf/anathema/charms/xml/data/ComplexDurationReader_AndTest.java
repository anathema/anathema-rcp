package net.sf.anathema.charms.xml.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.duration.DurationDto;
import net.sf.anathema.charms.xml.TestDocumentReader;

import org.junit.Before;
import org.junit.Test;

public class ComplexDurationReader_AndTest {

  private DurationDto duration;

  @Before
  public void createDuration() throws Exception {
    duration = readDuration("<complexDuration>" //$NON-NLS-1$
        + "<addition>" //$NON-NLS-1$
        + "<duration event=\"PerformanceEnds\"/>" //$NON-NLS-1$
        + "<duration trait=\"Essence\" unit=\"hour\"/>" //$NON-NLS-1$
        + "</addition>" //$NON-NLS-1$
        + "</complexDuration>"); //$NON-NLS-1$
  }

  @Test
  public void readsUnit() throws Exception {
    assertThat(duration.additions.get(1).trait.unit, is("hour")); //$NON-NLS-1$
  }

  @Test
  public void readsTrait() throws Exception {
    assertThat(duration.additions.get(1).trait.trait, is("Essence")); //$NON-NLS-1$
  }

  @Test
  public void readsEvent() throws Exception {
    assertThat(duration.additions.get(0).until, is("PerformanceEnds")); //$NON-NLS-1$
  }

  private DurationDto readDuration(String xml) throws Exception {
    TestDocumentReader reader = new TestDocumentReader();
    reader.setXml(xml);
    return new ComplexDurationReader(reader.readDocument()).read();
  }
}