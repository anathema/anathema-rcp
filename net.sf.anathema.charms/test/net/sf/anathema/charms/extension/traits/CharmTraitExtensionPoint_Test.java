package net.sf.anathema.charms.extension.traits;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.fake.MockIntegerAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.charms.traits.CharmTraits;
import net.sf.anathema.charms.traits.ICharmTraitMap;
import net.sf.anathema.charms.tree.CharmId;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class CharmTraitExtensionPoint_Test {

  private CharmId charmId;
  private MockStringAttribute charmIdAttribute;

  @Before
  public void createCharmId() throws Exception {
    charmId = new CharmId("pattern for {0}", "trait");
    charmIdAttribute = new MockStringAttribute("charmId", "pattern for {0}");
  }

  @Test
  public void returnsNullForUnknownCharmId() throws Exception {
    IExtensionPoint point = createEmptyExtensionPoint();
    CharmTraits charmTraits = extractCharmTraits(point);
    assertThat(charmTraits, is(nullValue()));
  }

  @Test
  public void readsDataForCharmIdPattern() throws Exception {
    IExtensionElement element = createExtensionElement(charmIdAttribute, ForEssenceMinimum(3), ForPrimaryMinimum(2));
    CharmTraits traits = extractCharmTraits(element);
    assertThat(traits.essenceMinimum, is(3));
    assertThat(traits.primaryMinimum, is(2));
  }

  @Test
  public void defaultsEssenceMinimumTo1() throws Exception {
    IExtensionElement element = createExtensionElement(charmIdAttribute, ForPrimaryMinimum(4));
    CharmTraits traits = extractCharmTraits(element);
    assertThat(traits.essenceMinimum, is(1));
  }

  @Test
  public void defaultsPrimaryMinimumTo1() throws Exception {
    IExtensionElement element = createExtensionElement(charmIdAttribute, ForEssenceMinimum(4));
    CharmTraits traits = extractCharmTraits(element);
    assertThat(traits.primaryMinimum, is(1));
  }

  private CharmTraits extractCharmTraits(IExtensionElement element) {
    IExtensionPoint extensionPoint = createExtensionPoint(element);
    return extractCharmTraits(extensionPoint);
  }

  private CharmTraits extractCharmTraits(IExtensionPoint extensionPoint) {
    ICharmTraitMap traitMap = new CharmTraitExtensionPoint(extensionPoint);
    return traitMap.getTraits(charmId);
  }

  private MockIntegerAttribute ForEssenceMinimum(int minimum) {
    return new MockIntegerAttribute("essenceMinimum", minimum);
  }

  private MockIntegerAttribute ForPrimaryMinimum(int minimum) {
    return new MockIntegerAttribute("primaryMinimum", minimum);
  }
}