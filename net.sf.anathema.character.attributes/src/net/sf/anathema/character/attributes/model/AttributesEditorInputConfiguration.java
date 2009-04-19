package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.groupeditor.AbstractEditorInputConfiguration;

public class AttributesEditorInputConfiguration extends AbstractEditorInputConfiguration {

  @Override
  public String getModelId() {
    return IAttributesPluginConstants.MODEL_ID;
  }

  @Override
  public String getGroupLabel(IDisplayTraitGroup< ? > group) {
    return AttributeMessages.get(group.getLabel());
  }
}