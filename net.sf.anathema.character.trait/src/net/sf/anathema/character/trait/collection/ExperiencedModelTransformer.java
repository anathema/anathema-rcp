package net.sf.anathema.character.trait.collection;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.interactive.IIntValueModel;

public final class ExperiencedModelTransformer implements ITransformer<IBasicTrait, IIntValueModel> {
  @Override
  public IIntValueModel transform(IBasicTrait trait) {
    return trait.getExperiencedModel();
  }
}