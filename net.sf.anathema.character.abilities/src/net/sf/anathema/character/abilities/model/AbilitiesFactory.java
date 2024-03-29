package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.abilities.template.AbilitiesTemplateProvider;
import net.sf.anathema.character.trait.collection.AbstractTraitCollectionFactory;
import net.sf.anathema.character.trait.collection.ITraitCollectionTemplateProvider;

public class AbilitiesFactory extends AbstractTraitCollectionFactory {

  @Override
  protected ITraitCollectionTemplateProvider getTemplateProvider() {
    return new AbilitiesTemplateProvider();
  }
}