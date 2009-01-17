package net.sf.anathema.charms.extension;

import static net.sf.anathema.charms.extension.DurationElementObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.MockChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockIntegerAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.charms.data.duration.DurationDto;

import org.junit.Test;

public class DurationReader_TraitTest {

  @Test
  public void readsTraitDurationsUnit() throws Exception {
    IExtensionElement trait = createTraitDuration("Strength", 1, "ticks"); //$NON-NLS-1$ //$NON-NLS-2$
    assertThat(readDuration(trait).additions.get(0).trait.unit, is("ticks")); //$NON-NLS-1$
  }

  @Test
  public void returnsTraitDurationsMultiplier() throws Exception {
    IExtensionElement trait = createTraitDuration("Strength", 4, "ticks"); //$NON-NLS-1$ //$NON-NLS-2$
    assertThat(readDuration(trait).additions.get(0).trait.multiplier, is(4));
  }

  @Test
  public void readsTraitDurationsTrait() throws Exception {
    IExtensionElement trait = createTraitDuration("Essence", 1, "ticks"); //$NON-NLS-1$ //$NON-NLS-2$
    assertThat(readDuration(trait).additions.get(0).trait.trait, is("Essence")); //$NON-NLS-1$
  }

  private DurationDto readDuration(IExtensionElement duration) {
    return new DurationReader(duration).read();
  }

  private static IExtensionElement createTraitDuration(String trait, int multiplier, String unit)
      throws ExtensionException {
    IExtensionElement amount = createTraitElement(trait, multiplier, unit);
    return createAdditiveDuration(new MockChildren(amount));
  }

  public static IExtensionElement createTraitElement(String trait, int multiplier, String unit)
      throws ExtensionException {
    MockStringAttribute valueAttribute = new MockStringAttribute("trait", trait); //$NON-NLS-1$
    MockIntegerAttribute multiplierAttribute = new MockIntegerAttribute("multiplier", multiplier); //$NON-NLS-1$
    MockStringAttribute unitAttribute = new MockStringAttribute("unit", unit); //$NON-NLS-1$
    return DurationElementObjectMother.createNamedElement("trait", valueAttribute, multiplierAttribute, unitAttribute);
  }
}