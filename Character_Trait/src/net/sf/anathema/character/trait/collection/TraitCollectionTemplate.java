package net.sf.anathema.character.trait.collection;

import net.sf.anathema.character.trait.model.IFavorizationTemplate;
import net.sf.anathema.character.trait.model.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;

public class TraitCollectionTemplate implements ITraitCollectionTemplate {

  private final ITraitGroupTemplate groupTemplate;
  private final IFavorizationTemplate favoredTemplate;

  public TraitCollectionTemplate(ITraitGroupTemplate groupTemplate, IFavorizationTemplate favoredTemplate) {
    this.groupTemplate = groupTemplate;
    this.favoredTemplate = favoredTemplate;
  }
  
  @Override
  public IFavorizationTemplate getFavorizationTemplate() {
    return favoredTemplate;
  }
  
  @Override
  public ITraitGroupTemplate getGroupTemplate() {
    return groupTemplate;
  }
}