package net.sf.anathema.charms.xml.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.duration.DurationDto;
import net.sf.anathema.charms.xml.TestDocumentReader;

import org.junit.Test;

public class SimpleDurationReader_TraitTest {

  @Test
  public void readsUnit() throws Exception {
    DurationDto duration = readDuration("<duration trait=\"Strength\" unit=\"scene\"/>"); //$NON-NLS-1$
    assertThat(duration.additions.get(0).trait.unit, is("scene")); //$NON-NLS-1$
  }

  @Test
  public void readsTrait() throws Exception {
    DurationDto duration = readDuration("<duration trait=\"Essence\" unit=\"scene\"/>"); //$NON-NLS-1$
    assertThat(duration.additions.get(0).trait.trait, is("Essence")); //$NON-NLS-1$
  }

  @Test
  public void readsMultiplier() throws Exception {
    DurationDto duration = readDuration("<duration trait=\"Essence\" multiplier=\"5\" unit=\"scene\"/>"); //$NON-NLS-1$
    assertThat(duration.additions.get(0).trait.multiplier, is(5));
  }

  private DurationDto readDuration(String xml) throws Exception {
    TestDocumentReader reader = new TestDocumentReader();
    reader.setXml(xml);
    return new SimpleDurationReader(reader.readDocument()).read();
  }
}