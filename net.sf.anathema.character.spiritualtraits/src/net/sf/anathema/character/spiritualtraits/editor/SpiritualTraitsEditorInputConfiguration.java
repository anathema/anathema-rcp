package net.sf.anathema.character.spiritualtraits.editor;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.spiritualtraits.display.SpiritualTraitLabelMap;
import net.sf.anathema.character.spiritualtraits.model.SpiritualTraitGroupTemplate;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.groupeditor.AbstractEditorInputConfiguration;
import net.sf.anathema.lib.collection.EqualsPredicate;
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
    String traitId = traitType.getId();
    if (traitId.equals(IPluginConstants.WILLPOWER_ID)) {
      return 10;
    }
    String[] virtueIds = new SpiritualTraitGroupTemplate().createVirtuesGroup().getTraitIds();
    if (ArrayUtilities.contains(virtueIds, new EqualsPredicate<String>(traitId))) {
      return 5;
    }
    return super.getTraitMaximum(traitType);
  }
}