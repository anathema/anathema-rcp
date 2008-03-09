package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.groupeditor.IEditorInputConfiguration;
import net.sf.anathema.lib.util.IIdentificate;

public class AbilitiesEditorInputConfiguration implements IEditorInputConfiguration {

  @Override
  public String getGroupLabel(IDisplayTraitGroup< ? > group) {
    return group.getId();
  }

  @Override
  public String getModelId() {
    return IAbilitiesPluginConstants.MODEL_ID;
  }

  @Override
  public String getTraitLabel(IIdentificate traitType) {
    // TODO Case 195: Internationalisierung von Abilties
    return traitType.getId();
  }
}