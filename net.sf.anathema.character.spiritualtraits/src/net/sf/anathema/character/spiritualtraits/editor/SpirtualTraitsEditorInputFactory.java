package net.sf.anathema.character.spiritualtraits.editor;

import net.sf.anathema.character.spiritualtraits.model.SpiritualTraitsTemplateProvider;
import net.sf.anathema.character.trait.collection.ITraitCollectionTemplateProvider;
import net.sf.anathema.character.trait.groupeditor.AbstractTraitCollectionEditorInputFactory;
import net.sf.anathema.character.trait.groupeditor.IEditorInputConfiguration;

public class SpirtualTraitsEditorInputFactory extends AbstractTraitCollectionEditorInputFactory {

  @Override
  protected IEditorInputConfiguration createEditorInputConfiguration() {
    return new SpiritualTraitsEditorInputConfiguration();
  }

  @Override
  protected ITraitCollectionTemplateProvider getTraitTemplateProvider() {
    return new SpiritualTraitsTemplateProvider();
  }
}