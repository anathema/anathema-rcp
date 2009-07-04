package net.sf.anathema.charms.extension;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.MockChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockNamedChild;
import net.sf.anathema.charms.data.duration.DurationDto;
import net.sf.anathema.charms.providing.DurationReader;

import org.junit.Before;
import org.junit.Test;

public class DurationReader_MinimumTest {

  private IExtensionElement duration;

  @Before
  public void createDuration() throws Exception {
    IExtensionElement until = DurationReader_UntilTest.createUntilElement("Performance ends"); //$NON-NLS-1$
    IExtensionElement trait = DurationReader_TraitTest.createTraitElement("Essence", 1, "hours"); //$NON-NLS-1$ //$NON-NLS-2$
    duration = createMinimumDuration(new MockChildren(until, trait));
  }

  @Test
  public void readsMultipleDurations() throws Exception {
    assertThat(readDuration().minimums.size(), is(2));
  }

  @Test
  public void containsUntilDuration() throws Exception {
    assertThat(readDuration().minimums.get(0).until, is("Performance ends")); //$NON-NLS-1$
  }

  private IExtensionElement createMinimumDuration(MockChildren mockChildren) throws ExtensionException {
    IExtensionElement minimum = DurationElementObjectMother.createNamedElement("minimum", mockChildren); //$NON-NLS-1$
    return DurationElementObjectMother.createDuration(new MockNamedChild("minimum", minimum), new MockChildren(minimum)); //$NON-NLS-1$
  }

  private DurationDto readDuration() {
    return new DurationReader(duration).read();
  }
}