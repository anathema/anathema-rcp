package net.sf.anathema.character.spiritualtraits.display;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.spiritualtraits.model.SpiritualTraitGroupTemplate;
import net.sf.anathema.character.spiritualtraits.model.SpiritualTraitsTemplateProvider;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.display.AbstractTraitDisplayGroupFactory;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;

public class SpiritualTraitDisplayGroupFactory extends AbstractTraitDisplayGroupFactory {

  public SpiritualTraitDisplayGroupFactory() {
    super(IPluginConstants.MODEL_ID, new SpiritualTraitsTemplateProvider());
  }

  @Override
  protected ITraitGroupTemplate createGroupTemplate(ICharacter character) {
    return new SpiritualTraitGroupTemplate();
  }
}