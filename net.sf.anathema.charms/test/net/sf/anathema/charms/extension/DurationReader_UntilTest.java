package net.sf.anathema.charms.extension;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.MockChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.charms.data.duration.DurationDto;

import org.junit.Test;

public class DurationReader_UntilTest {
  @Test
  public void returnsUntilDuration() throws Exception {
    IExtensionElement until = createUntilDuration("Dawn"); //$NON-NLS-1$
    assertThat(readDuration(until).additions.get(0).until, is("Dawn")); //$NON-NLS-1$
  }

  @Test
  public void readsUntilDurationsEvent() throws Exception {
    IExtensionElement until = createUntilDuration("the End"); //$NON-NLS-1$
    assertThat(readDuration(until).additions.get(0).until, is("the End")); //$NON-NLS-1$
  }

  @Test
  public void readsSingleDurations() throws Exception {
    IExtensionElement until = createUntilDuration("the End"); //$NON-NLS-1$
    assertThat(readDuration(until).additions.size(), is(1));
  }

  private DurationDto readDuration(IExtensionElement duration) {
    return new DurationReader().read(duration);
  }

  private static IExtensionElement createUntilDuration(String event) throws ExtensionException {
    IExtensionElement until = createUntilElement(event);
    return DurationElementObjectMother.createAdditiveDuration(new MockChildren(until));
  }

  public static IExtensionElement createUntilElement(String event) throws ExtensionException {
    MockStringAttribute eventAttribute = new MockStringAttribute("event", event); //$NON-NLS-1$
    IExtensionElement until = DurationElementObjectMother.createNamedElement("until", eventAttribute);
    return until;
  }
}