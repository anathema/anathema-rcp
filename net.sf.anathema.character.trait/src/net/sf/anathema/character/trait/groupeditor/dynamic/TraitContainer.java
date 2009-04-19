package net.sf.anathema.character.trait.groupeditor.dynamic;

import net.sf.anathema.character.core.traitview.IExtendableIntValueView;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.character.trait.resources.TraitMessages;

import org.eclipse.ui.forms.widgets.FormToolkit;

public class TraitContainer implements ITraitContainer {

  private final FormToolkit toolkit;
  private final TraitViewFactory factory;
  private final IDynamicEditor editor;
  private final TraitMessages traitMessages = new TraitMessages();

  public TraitContainer(FormToolkit toolkit, TraitViewFactory factory, IDynamicEditor editor) {
    this.toolkit = toolkit;
    this.factory = factory;
    this.editor = editor;
  }

  @Override
  public void addTrait(IInteractiveTrait trait) {
    String traitId = trait.getTraitType().getId();
    String label = traitMessages.getNameFor(traitId);
    IExtendableIntValueView view = factory.create(label, toolkit, trait);
    editor.decorate(trait, view);
    editor.addDisposable(trait);
  }

  protected final IDynamicEditor getEditor() {
    return editor;
  }
}