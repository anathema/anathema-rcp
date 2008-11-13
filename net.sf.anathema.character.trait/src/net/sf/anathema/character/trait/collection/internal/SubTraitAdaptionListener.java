package net.sf.anathema.character.trait.collection.internal;

import java.util.List;

import net.disy.commons.core.model.listener.IChangeListener;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.interactive.IIntValueModel;

public final class SubTraitAdaptionListener implements IChangeListener {
  private final IBasicTrait basicTrait;
  private final ITransformer<IBasicTrait, IIntValueModel> transformer;
  private final ITraitCollectionModel collection;

  public SubTraitAdaptionListener(
      ITraitCollectionModel collection,
      IBasicTrait basicTrait,
      ITransformer<IBasicTrait, IIntValueModel> transformer) {
    this.collection = collection;
    this.basicTrait = basicTrait;
    this.transformer = transformer;
  }

  @Override
  public void stateChanged() {
    List<IBasicTrait> subTraits = collection.getSubTraits(basicTrait.getTraitType().getId());
    if (subTraits.isEmpty()) {
      return;
    }
    IIntValueModel traitValueModel = transformer.transform(basicTrait);
    int maximalSubTrait = 0;
    for (IBasicTrait aSubTrait : subTraits) {
      IIntValueModel subTraitValueModel = transformer.transform(aSubTrait);
      maximalSubTrait = Math.max(subTraitValueModel.getValue(), maximalSubTrait);
    }
    traitValueModel.setValue(maximalSubTrait);
  }
}