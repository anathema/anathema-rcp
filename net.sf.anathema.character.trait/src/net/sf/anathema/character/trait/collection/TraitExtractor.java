package net.sf.anathema.character.trait.collection;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.trait.IBasicTrait;

public final class TraitExtractor implements ITransformer<String, IBasicTrait> {
  private final ITraitCollectionModel traitCollection;

  TraitExtractor(final ITraitCollectionModel traitCollection) {
    this.traitCollection = traitCollection;
  }

  @Override
  public IBasicTrait transform(final String id) {
    return traitCollection.getTrait(id);
  }
}