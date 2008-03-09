package net.sf.anathema.character.trait.collection;

import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;
import net.sf.anathema.character.trait.model.ITraitTemplateFactory;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.template.ITraitTemplate;

public class TraitCollectionTemplate implements ITraitCollectionTemplate {

  private final ITraitGroupTemplate groupTemplate;
  private final int favoredCount;
  private final ITraitTemplateFactory factory;

  public TraitCollectionTemplate(ITraitGroupTemplate groupTemplate, ITraitTemplateFactory factory, int favoredCount) {
    this.groupTemplate = groupTemplate;
    this.factory = factory;
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

  @Override
  public ITraitTemplate getTraitTemplate(String traitId) {
    return factory.getTraitTemplate(traitId);
  }
}