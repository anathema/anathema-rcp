package net.sf.anathema.character.spiritualtraits.model;

import net.sf.anathema.character.trait.collection.FavorizationTemplate;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;
import net.sf.anathema.character.trait.model.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;

public class SpiritualTraitCollectionTemplate implements ITraitCollectionTemplate {

  @Override
  public IFavorizationTemplate getFavorizationTemplate() {
    return new FavorizationTemplate(0);
  }

  @Override
  public ITraitGroupTemplate getGroupTemplate() {
    return new SpiritualTraitGroupTemplate();
  }
}