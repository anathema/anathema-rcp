package net.sf.anathema.charms.character.points;

import java.text.MessageFormat;

import net.sf.anathema.basics.eclipse.extension.AttributePredicate;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionPoint;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.type.CharacterTypeFinder;
import net.sf.anathema.character.points.cost.CostDto;
import net.sf.anathema.character.points.cost.CostTagDto;
import net.sf.anathema.charms.character.IPluginConstants;

public class CharmCostExtensionPoint {

  private static final String EXTENSION_POINT_ID = "charmcosts"; //$NON-NLS-1$
  private static final String ATTRIB_CHARACTER_TYPE = "characterType"; //$NON-NLS-1$
  private static final String TAG_EXPERIENCE = "experience"; //$NON-NLS-1$
  private static final String TAG_BONUS = "bonus"; //$NON-NLS-1$
  private static final String ATTRIB_EXPENSIVE = "expensive"; //$NON-NLS-1$
  private static final String ATTRIB_CHEAP = "cheap"; //$NON-NLS-1$
  private static final String TAG_COSTS = "costs"; //$NON-NLS-1$
  private final IExtensionPoint extensionPoint;

  public static CostDto getCost(ICharacterId characterId) {
    ICharacterType characterType = new CharacterTypeFinder().getCharacterType(characterId);
    return new CharmCostExtensionPoint().getCost(characterType);
  }

  public CharmCostExtensionPoint() {
    this(new EclipseExtensionPoint(IPluginConstants.PLUGIN_ID, EXTENSION_POINT_ID));
  }

  public CharmCostExtensionPoint(IExtensionPoint extensionPoint) {
    this.extensionPoint = extensionPoint;
  }

  public CostDto getCost(ICharacterType characterType) {
    CostDto resultCost = new CostDto();
    String characterTypeId = characterType.getId();
    AttributePredicate characterTypePredicate = AttributePredicate.FromNameAndValue(
        ATTRIB_CHARACTER_TYPE,
        characterTypeId);
    IExtensionElement costElement = extensionPoint.getFirst(characterTypePredicate);
    if (costElement == null) {
      String pattern = "No charm costs defined for character type {0}.";
      throw new IllegalStateException(MessageFormat.format(pattern, characterTypeId));
    }
    resultCost.experience = getCostTag(costElement.getElement(TAG_EXPERIENCE));
    resultCost.bonus = getCostTag(costElement.getElement(TAG_BONUS));
    return resultCost;
  }

  private CostTagDto getCostTag(IExtensionElement parent) {
    IExtensionElement costElement = parent.getElement(TAG_COSTS);
    CostTagDto costTag = new CostTagDto();
    costTag.cheap = costElement.getIntegerAttribute(ATTRIB_CHEAP);
    costTag.expensive = costElement.getIntegerAttribute(ATTRIB_EXPENSIVE);
    return costTag;
  }
}