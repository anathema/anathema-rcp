package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.groupeditor.AbstractEditorInputConfiguration;
import net.sf.anathema.lib.util.IIdentificate;

public class AbilitiesEditorInputConfiguration extends AbstractEditorInputConfiguration {

  @Override
  public String getGroupLabel(IDisplayTraitGroup< ? > group) {
    return group.getLabel();
  }

  @Override
  public String getModelId() {
    return IAbilitiesPluginConstants.MODEL_ID;
  }

  @Override
  public String getTraitLabel(IIdentificate traitType) {
    return AbilitiesMessages.get(traitType.getId());
  }

  @Override
  public boolean supportsSubTraits() {
    return true;
  }
}