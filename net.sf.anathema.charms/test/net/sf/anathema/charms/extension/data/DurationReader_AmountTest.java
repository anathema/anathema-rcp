package net.sf.anathema.charms.extension.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.MockChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.charms.data.duration.DurationDto;
import net.sf.anathema.charms.data.duration.PrimitiveDurationDto;
import net.sf.anathema.charms.extension.data.DurationReader;

import org.junit.Test;

public class DurationReader_AmountTest {

  @Test
  public void readsAmountDurationsUnit() throws Exception {
    IExtensionElement amount = createAmountElement("5", "wars"); //$NON-NLS-1$ //$NON-NLS-2$
    PrimitiveDurationDto primitiveDurationDto = readDuration(amount).additions.get(0);
    assertThat(primitiveDurationDto.amount.unit, is("wars")); //$NON-NLS-1$
  }

  @Test
  public void readsAmountDurationsAmount() throws Exception {
    IExtensionElement amount = createAmountElement("6", "ticks"); //$NON-NLS-1$ //$NON-NLS-2$
    assertThat(readDuration(amount).additions.get(0).amount.value, is("6")); //$NON-NLS-1$
  }

  private DurationDto readDuration(IExtensionElement duration) {
    return new DurationReader(duration).read();
  }

  public static IExtensionElement createAmountElement(String value, String unit) throws ExtensionException {
    MockStringAttribute valueAttribute = new MockStringAttribute("value", value); //$NON-NLS-1$
    MockStringAttribute unitAttribute = new MockStringAttribute("unit", unit); //$NON-NLS-1$
    IExtensionElement amount = DurationElementObjectMother.createNamedElement("amount", valueAttribute, unitAttribute);
    return DurationElementObjectMother.createAdditiveDuration(new MockChildren(amount));
  }
}