package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.character.core.traitview.IExtendableIntValueView;
import net.sf.anathema.character.trait.IDisplayTrait;

public interface ITraitGroupEditorDecoration {

  public void decorate(final IDisplayTrait trait, final IExtendableIntValueView view, ITraitGroupEditorInput editorInput);

}