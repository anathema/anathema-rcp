package net.sf.anathema.character.attributes.model;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.DisplayFavorization;
import net.sf.anathema.character.trait.DisplayTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.group.DisplayTraitGroup;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.rules.ITraitTemplate;

public final class TraitGroupToDisplayTraitGroupTransformer implements ITransformer<ITraitGroup, IDisplayTraitGroup> {

  private final IAttributeCharacterContext context;

  public TraitGroupToDisplayTraitGroupTransformer(IAttributeCharacterContext context) {
    this.context = context;
  }

  @Override
  public IDisplayTraitGroup transform(ITraitGroup group) {
    DisplayTraitGroup displayGroup = new DisplayTraitGroup(group.getId());
    for (String traitId : group.getTraitIds()) {
      IBasicTrait trait = context.getAttributes().getTrait(traitId);
      IExperience experience = context.getExperience();
      IFavorizationHandler favorizationHandler = context.getFavorizationHandler();
      ITraitTemplate traitTemplate = context.getTraitTemplate();
      DisplayFavorization favorization = new DisplayFavorization(trait, experience, favorizationHandler);
      displayGroup.addTrait(new DisplayTrait(trait, experience, favorization, traitTemplate));
    }
    return displayGroup;
  }
}