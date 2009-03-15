package net.sf.anathema.character.spiritualtraits.editor;

import net.sf.anathema.character.spiritualtraits.display.SpiritualTraitLabelMap;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.groupeditor.AbstractEditorInputConfiguration;
import net.sf.anathema.lib.util.IIdentificate;

public class SpiritualTraitsEditorInputConfiguration extends AbstractEditorInputConfiguration {

  @Override
  public String getModelId() {
    return IPluginConstants.MODEL_ID;
  }

  @Override
  public String getGroupLabel(IDisplayTraitGroup< ? > group) {
    return group.getLabel();
  }

  @Override
  public String getTraitLabel(IIdentificate traitType) {
    return SpiritualTraitLabelMap.getLabel(traitType.getId());
  }

  @Override
  public int getTraitMaximum(IIdentificate traitType) {
    if (traitType.getId().equals(IPluginConstants.WILLPOWER_ID)) {
      return 10;
    }
    return super.getTraitMaximum(traitType);
  }
}