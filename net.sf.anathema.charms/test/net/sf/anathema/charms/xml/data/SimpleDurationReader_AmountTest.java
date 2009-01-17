package net.sf.anathema.charms.xml.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.duration.DurationDto;
import net.sf.anathema.charms.xml.TestDocumentReader;

import org.junit.Test;

public class SimpleDurationReader_AmountTest {
  @Test
  public void readsUnit() throws Exception {
    DurationDto duration = readDuration("<duration amount=\"1\" unit=\"scene\"/>"); //$NON-NLS-1$
    assertThat(duration.additions.get(0).amount.unit, is("scene")); //$NON-NLS-1$
  }

  @Test
  public void readsAmount() throws Exception {
    DurationDto duration = readDuration("<duration amount=\"1\" unit=\"scene\"/>"); //$NON-NLS-1$
    assertThat(duration.additions.get(0).amount.value, is("1")); //$NON-NLS-1$
  }

  @Test
  public void readsNoTraitDuration() throws Exception {
    DurationDto duration = readDuration("<duration amount=\"1\" unit=\"scene\"/>"); //$NON-NLS-1$
    assertThat(duration.additions.get(0).trait, is(nullValue()));
  }

  @Test
  public void readsTextualAmounts() throws Exception {
    DurationDto duration = readDuration("<duration amount=\"Successes\" unit=\"scene\"/>"); //$NON-NLS-1$
    assertThat(duration.additions.get(0).amount.value, is("Successes")); //$NON-NLS-1$
  }

  private DurationDto readDuration(String xml) throws Exception {
    TestDocumentReader reader = new TestDocumentReader();
    reader.setXml(xml);
    return new SimpleDurationReader(reader.readDocument()).read();
  }
}
