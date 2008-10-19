package net.sf.anathema.charms.character;

import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.charms.view.ZestView;

import org.eclipse.swt.widgets.Composite;

public class CharmsEditorControl extends AbstractItemEditorControl {

  public CharmsEditorControl(CharmsEditor editor) {
    super(editor);
  }

  @Override
  public void createPartControl(Composite parent) {
    CharmsEditorInput editorInput = getEditorInput();
    ZestView zestView = new ZestView(new CharacterCharmVisuals(), editorInput.getTreeId());
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