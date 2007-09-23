package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.character.core.traitview.IExtendableIntValueView;
import net.sf.anathema.character.trait.IDisplayTrait;

import org.eclipse.core.runtime.IExecutableExtension;

public interface ITraitGroupEditorDecoration extends IExecutableExtension {

  public void decorate(final IDisplayTrait trait, final IExtendableIntValueView view, ITraitGroupEditorInput editorInput);
}