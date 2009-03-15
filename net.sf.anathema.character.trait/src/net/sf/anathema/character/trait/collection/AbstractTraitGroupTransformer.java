package net.sf.anathema.character.trait.collection;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.DisplayTraitGroup;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.ITraitGroup;

public abstract class AbstractTraitGroupTransformer<D extends IDisplayTrait> implements
    ITransformer<ITraitGroup, IDisplayTraitGroup<D>> {

  private final ITraitCollectionModel traitCollection;

  public AbstractTraitGroupTransformer(ITraitCollectionModel traitCollection) {
    this.traitCollection = traitCollection;
  }

  @Override
  public final IDisplayTraitGroup<D> transform(ITraitGroup group) {
    DisplayTraitGroup<D> displayGroup = new DisplayTraitGroup<D>(group.getId(), group.getLabel());
    for (String traitId : group.getTraitIds()) {
      displayGroup.addTrait(createTrait(traitId));
    }
    return displayGroup;
  }

  private D createTrait(String traitId) {
    IBasicTrait basicTrait = traitCollection.getTrait(traitId);
    return createTrait(basicTrait);
  }

  protected abstract D createTrait(IBasicTrait trait);
}