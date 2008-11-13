package net.sf.anathema.character.trait.collection.internal;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.interactive.IIntValueModel;

public final class CreationModelTransformer implements ITransformer<IBasicTrait, IIntValueModel> {
  @Override
  public IIntValueModel transform(IBasicTrait trait) {
    return trait.getCreationModel();
  }
}