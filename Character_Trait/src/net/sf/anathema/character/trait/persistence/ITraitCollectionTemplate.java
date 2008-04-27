package net.sf.anathema.character.trait.persistence;

import net.sf.anathema.character.core.model.template.IModelTemplate;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;
import net.sf.anathema.character.trait.model.IMinimalValueFactory;

public interface ITraitCollectionTemplate extends ITraitGroupTemplate, IMinimalValueFactory, IModelTemplate {

  public int getFavorizationCount();
}