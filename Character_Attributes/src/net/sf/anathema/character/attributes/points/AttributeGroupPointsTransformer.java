package net.sf.anathema.character.attributes.points;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.ITraitGroup;

public final class AttributeGroupPointsTransformer implements ITransformer<ITraitGroup, Dots> {

  private final ITraitCollectionModel attributes;

  public AttributeGroupPointsTransformer(ITraitCollectionModel attributes) {
    this.attributes = attributes;
  }

  @Override
  public Dots transform(ITraitGroup group) {
    return new Dots(attributes, group);
  }
}