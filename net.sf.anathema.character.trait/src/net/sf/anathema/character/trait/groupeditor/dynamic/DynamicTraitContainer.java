package net.sf.anathema.character.trait.groupeditor.dynamic;

import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

import org.eclipse.ui.forms.widgets.FormToolkit;

public class DynamicTraitContainer extends TraitContainer {

  public DynamicTraitContainer(FormToolkit toolkit, TraitViewFactory factory, IDynamicEditor editor) {
    super(toolkit, factory, editor);
  }

  @Override
  public void addTrait(IInteractiveTrait trait) {
    super.addTrait(trait);
    getEditor().redraw();
  }
}