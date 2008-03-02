package net.sf.anathema.character.trait.persistence;

import net.sf.anathema.character.core.model.template.IModelTemplate;
import net.sf.anathema.character.trait.model.ITraitGroupConfiguration;

public interface ITraitCollectionTemplate extends ITraitGroupConfiguration, IModelTemplate {

  public int getFavorizationCount();
}