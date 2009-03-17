package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.lib.util.IIdentificate;

public abstract class AbstractEditorInputConfiguration implements IEditorInputConfiguration {
  @Override
  public int getTraitMaximum(IIdentificate traitId) {
    return IBasicTrait.ESSENCE_MAX;
  }

  @Override
  public boolean supportsSubTraits() {
    return false;
  }
}