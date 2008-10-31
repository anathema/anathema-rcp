package net.sf.anathema.character.trait.groupeditor.subtrait;

import net.sf.anathema.character.core.traitview.IExtendableIntValueView;
import net.sf.anathema.character.trait.groupeditor.GroupEditor;
import net.sf.anathema.character.trait.groupeditor.ISubtraitContainer;
import net.sf.anathema.character.trait.groupeditor.ITraitGroupEditorDecoration;
import net.sf.anathema.character.trait.groupeditor.ITraitGroupEditorInput;
import net.sf.anathema.character.trait.groupeditor.TraitViewFactory;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

import org.eclipse.ui.forms.widgets.FormToolkit;

public class SubtraitContainer implements ISubtraitContainer {

  private final FormToolkit toolkit;
  private final TraitViewFactory factory;
  private final ITraitGroupEditorInput editorInput;
  private final GroupEditor editor;

  public SubtraitContainer(
      FormToolkit toolkit,
      TraitViewFactory factory,
      ITraitGroupEditorInput editorInput,
      GroupEditor editor) {
    this.toolkit = toolkit;
    this.factory = factory;
    this.editorInput = editorInput;
    this.editor = editor;
  }

  @Override
  public void addSubTrait(IInteractiveTrait trait) {
    String id = trait.getTraitType().getId();
    final IExtendableIntValueView view = factory.create(id, toolkit, trait);
    for (ITraitGroupEditorDecoration decoration : editor.getDecorations()) {
      decoration.decorate(trait, view, editorInput);
    }
    editor.addDisposable(trait);
    editor.redraw();
  }
}