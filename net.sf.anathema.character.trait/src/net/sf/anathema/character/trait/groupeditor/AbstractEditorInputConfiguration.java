package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.lib.util.IIdentificate;

public abstract class AbstractEditorInputConfiguration implements IEditorInputConfiguration {

  @Override
  public int getTraitMaximum(IIdentificate traitId) {
    return 5;
  }

  @Override
  public boolean supportsSubTraits() {
    return false;
  }
}