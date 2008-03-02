package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.lib.util.IIdentificate;

public interface IEditorInputConfiguration {

  public String getModelId();

  public String getGroupLabel(IDisplayTraitGroup< ? > group);

  public String getTraitLabel(IIdentificate traitType);
}