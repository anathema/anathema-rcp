package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.model.template.IModelTemplate;
import net.sf.anathema.character.trait.collection.FavorizationTemplate;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;
import net.sf.anathema.character.trait.model.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;

public class DummyAttributeTemplate implements IModelTemplate, ITraitCollectionTemplate {

  private final AttributeGroupTemplate groupTemplate = new AttributeGroupTemplate();
  private final IFavorizationTemplate favorizationTemplate;

  public DummyAttributeTemplate(int favorizationCount) {
    this.favorizationTemplate = new FavorizationTemplate(favorizationCount);
  }

  @Override
  public IFavorizationTemplate getFavorizationTemplate() {
    return favorizationTemplate;
  }

  @Override
  public ITraitGroupTemplate getGroupTemplate() {
    return groupTemplate;
  }
}