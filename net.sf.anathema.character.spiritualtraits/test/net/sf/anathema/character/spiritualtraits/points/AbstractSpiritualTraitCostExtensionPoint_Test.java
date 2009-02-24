package net.sf.anathema.character.spiritualtraits.points;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.fake.MockIntegerAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.MockNamedChild;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.character.spiritualtraits.points.essence.IEssenceCostMap;

import org.junit.Test;

@SuppressWarnings("nls")
public abstract class AbstractSpiritualTraitCostExtensionPoint_Test {

  private final String tagName;

  public AbstractSpiritualTraitCostExtensionPoint_Test(String tagName) {
    this.tagName = tagName;
  }

  protected static final int DEFINED_COST = 5;
  protected static final String DEFINED_TYPE = "myType";

  @Test
  public final void returnsDefinedCostForDefinedType() throws Exception {
    IEssenceCostMap costExtensionPoint = createCostExtensionPoint();
    assertThat(getPoints(costExtensionPoint, DEFINED_TYPE), is(DEFINED_COST));
  }

  protected abstract int getPoints(IEssenceCostMap costExtensionPoint, String configurationName);

  @Test
  public final void returnsZeroCostForUndefinedType() throws Exception {
    IEssenceCostMap costExtensionPoint = createCostExtensionPoint();
    assertThat(getPoints(costExtensionPoint, "undefinedType"), is(0));
  }

  protected IEssenceCostMap createCostExtensionPoint() throws Exception {
    IExtensionElement spiritualTraitsElement = createEssenceElement(DEFINED_COST, DEFINED_TYPE);
    IExtensionPoint extensionPoint = createExtensionPointForElement(spiritualTraitsElement);
    return new SpiritualTraitCostExtensionPoint(extensionPoint);
  }

  private IExtensionElement createEssenceElement(int value, String characterType) throws ExtensionException {
    IExtensionElement costElement = createExtensionElement(new MockIntegerAttribute("essence", value));
    IExtensionElement taggedPointsElement = createExtensionElement(new MockNamedChild("cost", costElement));
    MockStringAttribute characterTypeAttribute = new MockStringAttribute("characterType", characterType);
    MockNamedChild bonusPointsAttribute = new MockNamedChild(tagName, taggedPointsElement);
    return createExtensionElement(bonusPointsAttribute, characterTypeAttribute);
  }

  private IExtensionPoint createExtensionPointForElement(IExtensionElement spiritualTraitsElement) {
    IPluginExtension extension = createPluginExtension(spiritualTraitsElement);
    return createExtensionPoint(extension);
  }
}