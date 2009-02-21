package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.abilities.template.AbilitiesTemplateProvider;
import net.sf.anathema.character.trait.collection.ITraitCollectionTemplateProvider;
import net.sf.anathema.character.trait.groupeditor.AbstractTraitCollectionEditorInputFactory;

public class AbilitiesEditorInputFactory extends AbstractTraitCollectionEditorInputFactory {

  @Override
  protected AbilitiesEditorInputConfiguration createEditorInputConfiguration() {
    return new AbilitiesEditorInputConfiguration();
  }

  @Override
  protected ITraitCollectionTemplateProvider getTraitTemplateProvider() {
    return new AbilitiesTemplateProvider();
  }
}