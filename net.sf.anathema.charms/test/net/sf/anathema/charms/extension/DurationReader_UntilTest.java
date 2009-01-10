package net.sf.anathema.charms.extension;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.MockChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockIntegerAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.MockName;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.charms.data.duration.DurationDto;

import org.junit.Test;

public class DurationReader_UntilTest {
  @Test
  public void returnsUntilDuration() throws Exception {
    IExtensionElement until = createUntilElement("Dawn");
    IExtensionElement duration = createExtensionElementWithAttributes(new MockChildren(until));
    DurationDto dto = new DurationReader().read(duration);
    assertThat(dto.until, is("Dawn"));
  }

  @Test
  public void readsUntilDurationsEvent() throws Exception {
    IExtensionElement until = createUntilElement("the End");
    IExtensionElement duration = createExtensionElementWithAttributes(new MockChildren(until));
    DurationDto dto = new DurationReader().read(duration);
    assertThat(dto.until, is("the End"));
  }

  @Test
  public void returnsKeywordDuration() throws Exception {
    IExtensionElement text = createKeywordElement("Instant"); //$NON-NLS-1$
    IExtensionElement duration = createExtensionElementWithAttributes(new MockChildren(text));
    DurationDto dto = new DurationReader().read(duration);
    assertThat(dto.keyword, is("Instant"));
  }

  @Test
  public void readsKeywordDuration() throws Exception {
    IExtensionElement text = createKeywordElement("Indefinite"); //$NON-NLS-1$
    IExtensionElement duration = createExtensionElementWithAttributes(new MockChildren(text));
    DurationDto dto = new DurationReader().read(duration);
    assertThat(dto.keyword, is("Indefinite"));
  }

  @Test
  public void returnsAmountDurationsUnit() throws Exception {
    IExtensionElement amount = createAmountElement(5, "ticks"); //$NON-NLS-1$
    IExtensionElement duration = createExtensionElementWithAttributes(new MockChildren(amount));
    DurationDto dto = new DurationReader().read(duration);
    assertThat(dto.amount.unit, is("ticks")); //$NON-NLS-1$
  }

  @Test
  public void returnsAmountDurationsAmount() throws Exception {
    IExtensionElement amount = createAmountElement(5, "ticks"); //$NON-NLS-1$
    IExtensionElement duration = createExtensionElementWithAttributes(new MockChildren(amount));
    DurationDto dto = new DurationReader().read(duration);
    assertThat(dto.amount.amount, is(5));
  }

  @Test
  public void readsAmountDurationsUnit() throws Exception {
    IExtensionElement amount = createAmountElement(5, "wars"); //$NON-NLS-1$
    IExtensionElement duration = createExtensionElementWithAttributes(new MockChildren(amount));
    DurationDto dto = new DurationReader().read(duration);
    assertThat(dto.amount.unit, is("wars")); //$NON-NLS-1$
  }

  @Test
  public void readsAmountDurationsAmount() throws Exception {
    IExtensionElement amount = createAmountElement(6, "ticks"); //$NON-NLS-1$
    IExtensionElement duration = createExtensionElementWithAttributes(new MockChildren(amount));
    DurationDto dto = new DurationReader().read(duration);
    assertThat(dto.amount.amount, is(6));
  }

  @Test
  public void returnsTraitDurationsAmount() throws Exception {
    IExtensionElement trait = createTraitElement("Strength", "ticks"); //$NON-NLS-1$
    IExtensionElement duration = createExtensionElementWithAttributes(new MockChildren(trait));
    DurationDto dto = new DurationReader().read(duration);
    assertThat(dto.trait.trait, is("Strength"));
  }

  @Test
  public void returnsTraitDurationsUnit() throws Exception {
    IExtensionElement trait = createTraitElement("Strength", "minutes"); //$NON-NLS-1$
    IExtensionElement duration = createExtensionElementWithAttributes(new MockChildren(trait));
    DurationDto dto = new DurationReader().read(duration);
    assertThat(dto.trait.unit, is("minutes"));
  }

  @Test
  public void readsTraitDurationsTrait() throws Exception {
    IExtensionElement trait = createTraitElement("Essence", "ticks"); //$NON-NLS-1$
    IExtensionElement duration = createExtensionElementWithAttributes(new MockChildren(trait));
    DurationDto dto = new DurationReader().read(duration);
    assertThat(dto.trait.trait, is("Essence"));
  }

  @Test
  public void readsTraitDurationsUnit() throws Exception {
    IExtensionElement trait = createTraitElement("Strength", "ticks"); //$NON-NLS-1$
    IExtensionElement duration = createExtensionElementWithAttributes(new MockChildren(trait));
    DurationDto dto = new DurationReader().read(duration);
    assertThat(dto.trait.unit, is("ticks"));
  }

  @Test
  public void hasNoAmountDurationIfTraitDurationIsSpecified() throws Exception {
    IExtensionElement trait = createTraitElement("Strength", "ticks"); //$NON-NLS-1$
    IExtensionElement duration = createExtensionElementWithAttributes(new MockChildren(trait));
    DurationDto dto = new DurationReader().read(duration);
    assertThat(dto.amount, is(nullValue()));
  }

  @Test
  public void hasNoTraitDurationIfAmountDurationIsSpecified() throws Exception {
    IExtensionElement amount = createAmountElement(5, "ticks"); //$NON-NLS-1$
    IExtensionElement duration = createExtensionElementWithAttributes(new MockChildren(amount));
    DurationDto dto = new DurationReader().read(duration);
    assertThat(dto.trait, is(nullValue()));
  }

  private IExtensionElement createTraitElement(String trait, String unit) throws ExtensionException {
    return createExtensionElementWithAttributes(
        new MockName("trait"),
        new MockStringAttribute("trait", trait),
        new MockStringAttribute("unit", unit));
  }

  private IExtensionElement createAmountElement(int amount, String unit) throws ExtensionException {
    return createExtensionElementWithAttributes(
        new MockName("amount"),
        new MockIntegerAttribute("amount", amount),
        new MockStringAttribute("unit", unit));
  }

  private IExtensionElement createKeywordElement(String keyword) throws ExtensionException {
    return createExtensionElementWithAttributes(new MockName("keyword"), new MockStringAttribute("keyword", keyword));
  }

  private IExtensionElement createUntilElement(String event) throws ExtensionException {
    return createExtensionElementWithAttributes(new MockName("until"), new MockStringAttribute("event", event));
  }
}