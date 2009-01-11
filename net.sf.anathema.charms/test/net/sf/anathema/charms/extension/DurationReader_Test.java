package net.sf.anathema.charms.extension;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.MockChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockName;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.charms.data.duration.DurationDto;

import org.junit.Test;

public class DurationReader_Test {
  @Test
  public void returnsUntilDuration() throws Exception {
    IExtensionElement until = createUntilElement("Dawn"); //$NON-NLS-1$
    assertThat(readDuration(until).until, is("Dawn")); //$NON-NLS-1$
  }

  @Test
  public void readsUntilDurationsEvent() throws Exception {
    IExtensionElement until = createUntilElement("the End"); //$NON-NLS-1$
    assertThat(readDuration(until).until, is("the End")); //$NON-NLS-1$
  }

  @Test
  public void returnsKeywordDuration() throws Exception {
    IExtensionElement text = createKeywordElement("Instant"); //$NON-NLS-1$
    assertThat(readDuration(text).keyword, is("Instant")); //$NON-NLS-1$
  }

  @Test
  public void readsKeywordDuration() throws Exception {
    IExtensionElement text = createKeywordElement("Indefinite"); //$NON-NLS-1$
    assertThat(readDuration(text).keyword, is("Indefinite")); //$NON-NLS-1$
  }

  @Test
  public void readsAmountDurationsUnit() throws Exception {
    IExtensionElement amount = createAmountElement("5", "wars"); //$NON-NLS-1$ //$NON-NLS-2$
    assertThat(readDuration(amount).amount.unit, is("wars")); //$NON-NLS-1$
  }

  @Test
  public void readsTraitDurationsUnit() throws Exception {
    IExtensionElement trait = createTraitElement("Strength", "ticks"); //$NON-NLS-1$ //$NON-NLS-2$
    assertThat(readDuration(trait).amount.unit, is("ticks")); //$NON-NLS-1$
  }

  @Test
  public void readsAmountDurationsAmount() throws Exception {
    IExtensionElement amount = createAmountElement("6", "ticks"); //$NON-NLS-1$ //$NON-NLS-2$
    assertThat(readDuration(amount).amount.value, is("6")); //$NON-NLS-1$
  }

  @Test
  public void returnsTraitDurationsAmount() throws Exception {
    IExtensionElement trait = createTraitElement("Strength", "ticks"); //$NON-NLS-1$ //$NON-NLS-2$
    assertThat(readDuration(trait).amount.trait, is("Strength")); //$NON-NLS-1$
  }

  @Test
  public void readsTraitDurationsTrait() throws Exception {
    IExtensionElement trait = createTraitElement("Essence", "ticks"); //$NON-NLS-1$ //$NON-NLS-2$
    assertThat(readDuration(trait).amount.trait, is("Essence")); //$NON-NLS-1$
  }

  @Test
  public void hasNoAmountDurationIfTraitDurationIsSpecified() throws Exception {
    IExtensionElement trait = createTraitElement("Strength", "ticks"); //$NON-NLS-1$ //$NON-NLS-2$
    assertThat(readDuration(trait).amount.value, is(nullValue()));
  }

  @Test
  public void hasNoTraitDurationIfAmountDurationIsSpecified() throws Exception {
    IExtensionElement amount = createAmountElement("5", "ticks"); //$NON-NLS-1$ //$NON-NLS-2$
    assertThat(readDuration(amount).amount.trait, is(nullValue()));
  }

  private DurationDto readDuration(IExtensionElement amount) throws ExtensionException {
    IExtensionElement duration = createExtensionElementWithAttributes(new MockChildren(amount));
    return new DurationReader().read(duration);
  }

  private IExtensionElement createTraitElement(String trait, String unit) throws ExtensionException {
    return createExtensionElementWithAttributes(new MockName("trait"), //$NON-NLS-1$
        new MockStringAttribute("trait", trait), //$NON-NLS-1$
        new MockStringAttribute("unit", unit)); //$NON-NLS-1$
  }

  private IExtensionElement createAmountElement(String amount, String unit) throws ExtensionException {
    return createExtensionElementWithAttributes(new MockName("amount"), //$NON-NLS-1$
        new MockStringAttribute("value", amount), //$NON-NLS-1$
        new MockStringAttribute("unit", unit)); //$NON-NLS-1$
  }

  private IExtensionElement createKeywordElement(String keyword) throws ExtensionException {
    return createExtensionElementWithAttributes(new MockName("keyword"), new MockStringAttribute("keyword", keyword)); //$NON-NLS-1$ //$NON-NLS-2$
  }

  private IExtensionElement createUntilElement(String event) throws ExtensionException {
    return createExtensionElementWithAttributes(new MockName("until"), new MockStringAttribute("event", event)); //$NON-NLS-1$ //$NON-NLS-2$
  }
}