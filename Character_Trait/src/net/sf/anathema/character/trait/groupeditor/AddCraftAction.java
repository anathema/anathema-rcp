package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

import org.eclipse.jface.action.Action;

public final class AddCraftAction extends Action {
  private final ITraitGroupEditorInput editorInput;
  private final ISubtraitContainer subtraitContainer;

  public AddCraftAction(ITraitGroupEditorInput editorInput, ISubtraitContainer subtraitContainer) {
    super("Add Craft");
    this.editorInput = editorInput;
    this.subtraitContainer = subtraitContainer;
  }

  @Override
  public void run() {
    IInteractiveTrait interactiveTrait = editorInput.addSubTrait("Craft", "Craft generic"); //$NON-NLS-1$
    subtraitContainer.addSubTrait(interactiveTrait);
  }
}