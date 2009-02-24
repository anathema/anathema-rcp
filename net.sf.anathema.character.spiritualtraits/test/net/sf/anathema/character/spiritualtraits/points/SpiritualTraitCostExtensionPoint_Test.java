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

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class SpiritualTraitCostExtensionPoint_Test {

  private static final int DEFINED_COST = 5;
  private static final String DEFINED_TYPE = "myType";
  private IEssenceCostMap costExtensionPoint;

  @Before
  public void methodName() throws Exception {
    IExtensionElement spiritualTraitsElement = createEssenceElement(DEFINED_COST, DEFINED_TYPE);
    IExtensionPoint extensionPoint = createExtensionPointForElement(spiritualTraitsElement);
    costExtensionPoint = new SpiritualTraitCostExtensionPoint(extensionPoint);
  }

  @Test
  public void returnsDefinedCostForDefinedType() throws Exception {
    assertThat(costExtensionPoint.getEssenceBonuspointCost(DEFINED_TYPE), is(DEFINED_COST));
  }

  @Test
  public void returnsZeroForUndefinedType() throws Exception {
    assertThat(costExtensionPoint.getEssenceBonuspointCost("undefinedType"), is(0));
  }

  private IExtensionPoint createExtensionPointForElement(IExtensionElement spiritualTraitsElement) {
    IPluginExtension extension = createPluginExtension(spiritualTraitsElement);
    return createExtensionPoint(extension);
  }

  private IExtensionElement createEssenceElement(int value, String characterType) throws ExtensionException {
    IExtensionElement costElement = createExtensionElement(new MockIntegerAttribute("essence", value));
    IExtensionElement bonusPointsElement = createExtensionElement(new MockNamedChild("cost", costElement));
    MockStringAttribute characterTypeAttribute = new MockStringAttribute("characterType", characterType);
    MockNamedChild bonusPointsAttribute = new MockNamedChild("bonuspoints", bonusPointsElement);
    return createExtensionElement(bonusPointsAttribute, characterTypeAttribute);
  }
}