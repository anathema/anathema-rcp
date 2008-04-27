package net.sf.anathema.character.trait.persistence;

import net.sf.anathema.character.core.model.template.IModelTemplate;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;

public interface ITraitCollectionTemplate extends ITraitGroupTemplate, IModelTemplate {

  public int getFavorizationCount();
}