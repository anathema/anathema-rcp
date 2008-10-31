package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.groupeditor.IEditorInputConfiguration;
import net.sf.anathema.lib.util.IIdentificate;

public class AttributesEditorInputConfiguration implements IEditorInputConfiguration {

  @Override
  public String getModelId() {
    return IAttributesPluginConstants.MODEL_ID;
  }

  @Override
  public String getGroupLabel(IDisplayTraitGroup< ? > group) {
    return AttributeMessages.get(group.getLabel());
  }

  @Override
  public String getTraitLabel(IIdentificate traitType) {
    return AttributeMessages.get(traitType.getId());
  }

  @Override
  public boolean supportsSubTraits() {
    return false;
  }
}