package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.character.core.traitview.IExtendableIntValueView;
import net.sf.anathema.character.trait.IInteractiveTrait;

import org.eclipse.core.runtime.IExecutableExtension;

public interface ITraitGroupEditorDecoration extends IExecutableExtension {

  public void decorate(final IInteractiveTrait trait, final IExtendableIntValueView view, ITraitGroupEditorInput editorInput);
}