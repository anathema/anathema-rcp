package net.sf.anathema.character.trait.model;

import net.sf.anathema.character.core.model.template.IModelTemplate;

public interface ITraitCollectionTemplate extends IModelTemplate {

  public ITraitGroupTemplate getGroupTemplate();

  public IFavorizationTemplate getFavorizationTemplate();
}