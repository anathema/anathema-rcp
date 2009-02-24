package net.sf.anathema.character.spiritualtraits.points;

import net.sf.anathema.basics.eclipse.extension.AttributePredicate;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionPoint;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.spiritualtraits.points.essence.IEssenceCostMap;

public class SpiritualTraitCostExtensionPoint implements IEssenceCostMap {

  private static final String ATTRIB_ESSENCE = "essence"; //$NON-NLS-1$
  private static final String TAG_COST = "cost"; //$NON-NLS-1$
  private static String TAG_BONUSPOINTS = "bonuspoints"; //$NON-NLS-1$
  private static final String ATTRIB_CHARACTER_TYPE = "characterType"; //$NON-NLS-1$
  private static final String EXTENSION_POINT = "spiritualTraitCosts"; //$NON-NLS-1$
  private final IExtensionPoint extensionPoint;

  public SpiritualTraitCostExtensionPoint() {
    this(new EclipseExtensionPoint(IPluginConstants.PLUGIN_ID, EXTENSION_POINT));
  }

  public SpiritualTraitCostExtensionPoint(IExtensionPoint extensionPoint) {
    this.extensionPoint = extensionPoint;
  }

  public int getEssenceBonuspointCost(String characterType) {
    AttributePredicate predicate = AttributePredicate.FromNameAndValue(ATTRIB_CHARACTER_TYPE, characterType);
    IExtensionElement characterTypeElement = extensionPoint.getFirst(predicate);
    if (characterTypeElement == null) {
      return 0;
    }
    return getEssenceCost(characterTypeElement, TAG_BONUSPOINTS);
  }

  private int getEssenceCost(IExtensionElement characterTypeElement, String elementName) {
    IExtensionElement bonuspointCostsElement = characterTypeElement.getElement(elementName);
    IExtensionElement costElement = bonuspointCostsElement.getElement(TAG_COST);
    return costElement.getIntegerAttribute(ATTRIB_ESSENCE);
  }
}