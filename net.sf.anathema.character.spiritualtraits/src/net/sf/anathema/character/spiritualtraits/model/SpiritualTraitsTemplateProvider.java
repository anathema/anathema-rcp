package net.sf.anathema.character.spiritualtraits.model;

import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.collection.TraitCollectionTemplateProvider;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;

public class SpiritualTraitsTemplateProvider extends TraitCollectionTemplateProvider {

  public SpiritualTraitsTemplateProvider() {
    super(IPluginConstants.MODEL_ID);
  }

  @Override
  protected ITraitGroupTemplate createGroupTemplate(String characterTemplateId) {
    return new SpiritualTraitGroupTemplate();
  }
}