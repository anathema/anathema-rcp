package net.sf.anathema.character.trait.collection;

import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;

public class TraitCollectionTemplate implements ITraitCollectionTemplate {

  private final ITraitGroupTemplate groupTemplate;
  private final int favoredCount;

  public TraitCollectionTemplate(ITraitGroupTemplate groupTemplate, int favoredCount) {
    this.groupTemplate = groupTemplate;
    this.favoredCount = favoredCount;
  }

  @Override
  public int getFavorizationCount() {
    return favoredCount;
  }

  @Override
  public TraitGroup[] getGroups() {
    return groupTemplate.getGroups();
  }
}