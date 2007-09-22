package net.sf.anathema.character.attributes.model;

import java.text.MessageFormat;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.attributes.points.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.attributes.points.coverage.AttributeGroupPriorityCalculator;
import net.sf.anathema.character.attributes.points.coverage.PointCoverageCalculator;
import net.sf.anathema.character.core.plugin.ICharacterCorePluginConstants;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.groupeditor.IDecoratedIntViewConfiguration;
import net.sf.anathema.character.trait.resources.ITraitResources;
import net.sf.anathema.lib.util.IIdentificate;

import org.eclipse.swt.graphics.Image;

public class SurplusDecoratedIntViewConfiguration implements IDecoratedIntViewConfiguration {

  private final ITraitCollectionContext context;
  //private final Map<PriorityGroup, Integer> creditByPriority = new HashMap<PriorityGroup, Integer>();

  public SurplusDecoratedIntViewConfiguration(ITraitCollectionContext context) {
    this.context = context;
//  for (PriorityGroup group : PriorityGroup.values()) {
//  String creditId = determineCreditId(group);
//  int credit = new CreditManager().getCredit(getModelIdentifier().getCharacterId(), creditId);
//  creditByPriority.put(group, credit);
//}
  }

//private String determineCreditId(PriorityGroup priority) {
//  switch (priority) {
//    case Primary:
//      return new PrimaryAttributeFreebies().getCreditId();
//    case Secondary:
//      return new SecondaryAttributeFreebies().getCreditId();
//    case Tertiary:
//      return new TertiaryAttributeFreebies().getCreditId();
//  }
//  throw new UnreachableCodeReachedException();
//}

  @Override
  public Image createPassiveImage() {
    return createImage(ITraitResources.UNSELECTED_BUTTON);
  }

  @Override
  public Image createActiveImage() {
    return createImage(context.getActiveImageId());
  }

  @Override
  public Image createSurplusImage() {
    return createImage(ITraitResources.SURPLUS_BUTTON);
  }

  private Image createImage(String imageName) {
    return ICharacterCorePluginConstants.IMAGE_REGISTRY.get(imageName);
  }

  @Override
  public int getPointsCoveredByCredit(IIdentificate traitType) {
    return 0;
    // TODO Hängt von Freebies ab und das wollen wir doch gar nicht hier haben
//    ITraitGroup traitGroup = findTraitGroup(traitType);
//    PriorityGroup priority = new AttributeGroupPriorityCalculator(context).getPriority(traitGroup);
//    int credit = creditByPriority.get(priority);
//    PointCoverageCalculator calculator = new PointCoverageCalculator(context, credit);
//    return calculator.calculateCoverageFor(traitGroup).getPointsCovered(traitType);
  }

  private ITraitGroup findTraitGroup(IIdentificate traitType) {
    for (ITraitGroup group : context.getTraitGroups()) {
      if (ArrayUtilities.contains(group.getTraitIds(), traitType.getId())) {
        return group;
      }
    }
    Object[] arguments = new Object[] { traitType.getId() };
    throw new IllegalArgumentException(MessageFormat.format(
        Messages.AttributesEditorInput_GroupLessTraitMessage,
        arguments));
  }
}