package net.sf.anathema.character.trait.collection;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.DisplayTraitGroup;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.ITraitGroup;

public abstract class AbstractTraitGroupTransformer<T extends IDisplayTrait> implements
    ITransformer<ITraitGroup, IDisplayTraitGroup<T>> {

  private final ITraitCollectionContext context;

  public AbstractTraitGroupTransformer(ITraitCollectionContext context) {
    this.context = context;
  }

  @Override
  public final IDisplayTraitGroup<T> transform(ITraitGroup group) {
    DisplayTraitGroup<T> displayGroup = new DisplayTraitGroup<T>(group.getId(), group.getLabel());
    for (String traitId : group.getTraitIds()) {
      IBasicTrait trait = context.getCollection().getTrait(traitId);
      IModelContainer experience = context.getModelContainer();
      int minimalValue = context.getMinimumValue(traitId);
      displayGroup.addTrait(createTrait(trait, experience, minimalValue));
    }
    return displayGroup;
  }

  protected abstract T createTrait(IBasicTrait trait, IModelContainer container, int minimalValue);
}