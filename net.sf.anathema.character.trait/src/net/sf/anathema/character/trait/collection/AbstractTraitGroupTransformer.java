package net.sf.anathema.character.trait.collection;

import java.util.List;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.DisplayTraitGroup;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.validator.IValidator;

public abstract class AbstractTraitGroupTransformer<D extends IDisplayTrait> implements
    ITransformer<ITraitGroup, IDisplayTraitGroup<D>> {

  private final ITraitCollectionContext context;

  public AbstractTraitGroupTransformer(ITraitCollectionContext context) {
    this.context = context;
  }

  @Override
  public final IDisplayTraitGroup<D> transform(ITraitGroup group) {
    DisplayTraitGroup<D> displayGroup = new DisplayTraitGroup<D>(group.getId(), group.getLabel());
    for (String traitId : group.getTraitIds()) {
      IBasicTrait basicTrait = context.getCollection().getTrait(traitId);
      IExperience experience = context.getExperience();
      List<IValidator> validators = context.getValidators(traitId);
      D displayTrait = createTrait(basicTrait, experience, validators);
      displayGroup.addTrait(displayTrait);
    }
    return displayGroup;
  }

  protected abstract D createTrait(IBasicTrait trait, IExperience experience, List<IValidator> validators);
}