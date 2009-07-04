package net.sf.anathema.charms.extension;

import static net.sf.anathema.charms.extension.DurationElementObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.MockChildren;
import net.sf.anathema.charms.data.duration.DurationDto;
import net.sf.anathema.charms.providing.DurationReader;

import org.junit.Before;
import org.junit.Test;

public class DurationReader_AdditionTest {

  private IExtensionElement duration;

  @Before
  public void createDuration() throws Exception {
    IExtensionElement until = DurationReader_UntilTest.createUntilElement("Performance ends"); //$NON-NLS-1$
    IExtensionElement trait = DurationReader_TraitTest.createTraitElement("Essence", 1, "hours"); //$NON-NLS-1$ //$NON-NLS-2$
    duration = createAdditiveDuration(new MockChildren(until, trait));
  }

  @Test
  public void readsMultipleDurations() throws Exception {
    assertThat(readDuration().additions.size(), is(2));
  }

  private DurationDto readDuration() {
    return new DurationReader(duration).read();
  }
}