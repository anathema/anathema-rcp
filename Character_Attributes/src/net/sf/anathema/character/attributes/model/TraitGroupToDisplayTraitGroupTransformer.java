package net.sf.anathema.character.attributes.model;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.trait.DisplayTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.group.DisplayTraitGroup;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.ITraitGroup;

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
      displayGroup.addTrait(new DisplayTrait(trait, context.getExperience(), context.getTraitTemplate()));
    }
    return displayGroup;
  }
}