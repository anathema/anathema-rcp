package net.sf.anathema.character.backgrounds;

import net.sf.anathema.character.backgrounds.model.IBackgroundModel;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.groupeditor.AbstractEditorInputConfiguration;
import net.sf.anathema.lib.util.IIdentificate;

public class BackgroundConfiguration extends AbstractEditorInputConfiguration {

  @Override
  public String getGroupLabel(IDisplayTraitGroup< ? > group) {
    throw new UnsupportedOperationException(Messages.BackgroundConfiguration_Error_No_Groups);
  }

  @Override
  public int getTraitMaximum(IIdentificate traitId) {
    return 5;
  }

  @Override
  public String getModelId() {
    return IBackgroundModel.MODEL_ID;
  }
}