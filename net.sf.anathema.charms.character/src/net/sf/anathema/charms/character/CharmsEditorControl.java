package net.sf.anathema.charms.character;

import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.charms.view.ZestView;

import org.eclipse.swt.widgets.Composite;

public class CharmsEditorControl extends AbstractItemEditorControl {

  private final IExperience experience;

  public CharmsEditorControl(CharmsEditor editor, IExperience experience) {
    super(editor);
    this.experience = experience;
  }

  @Override
  public void createPartControl(Composite parent) {
    CharmsEditorInput editorInput = getEditorInput();
    CharacterCharmVisuals charmVisuals = new CharacterCharmVisuals(editorInput.getItem(), experience);
    ZestView zestView = new ZestView(charmVisuals, editorInput.getTreeId(), experience);
    zestView.createPartControl(parent);
    addDisposable(zestView);
  }

  public CharmsEditorInput getEditorInput() {
    return (CharmsEditorInput) getEditor().getPersistableEditorInput();
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}