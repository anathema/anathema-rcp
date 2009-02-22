package net.sf.anathema.character.attributes.util;

import net.sf.anathema.character.attributes.model.AttributeGroupTemplate;
import net.sf.anathema.character.attributes.model.AttributesTemplateProvider;
import net.sf.anathema.character.attributes.model.IAttributesPluginConstants;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.trait.display.AbstractTraitDisplayGroupFactory;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;

public class AttributeDisplayGroupFactory extends AbstractTraitDisplayGroupFactory {

  public AttributeDisplayGroupFactory() {
    super(IAttributesPluginConstants.MODEL_ID, new AttributesTemplateProvider());
  }

  @Override
  protected ITraitGroupTemplate createGroupTemplate(ICharacter character) {
    return new AttributeGroupTemplate();
  }
}