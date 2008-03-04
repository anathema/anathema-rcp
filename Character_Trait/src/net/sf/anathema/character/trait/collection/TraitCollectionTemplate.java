package net.sf.anathema.character.trait.collection;

import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.model.ITraitGroupConfiguration;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.template.ITraitTemplate;

public class TraitCollectionTemplate implements ITraitCollectionTemplate {

  private final ITraitGroupConfiguration groupConfiguration;
  private final int favoredCount;

  public TraitCollectionTemplate(ITraitGroupConfiguration groupConfiguration, int favoredCount) {
    this.groupConfiguration = groupConfiguration;
    this.favoredCount = favoredCount;
  }

  @Override
  public int getFavorizationCount() {
    return favoredCount;
  }

  @Override
  public TraitGroup[] getGroups() {
    return groupConfiguration.getGroups();
  }

  @Override
  public ITraitTemplate getTraitTemplate() {
    return groupConfiguration.getTraitTemplate();
  }
}