package net.sf.anathema.charms.character;

import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.basics.item.editor.IPersistableItemEditor;
import net.sf.anathema.charms.view.ZestView;

import org.eclipse.swt.widgets.Composite;

public class CharmsEditorControl extends AbstractItemEditorControl {

  public CharmsEditorControl(IPersistableItemEditor editor) {
    super(editor);
  }

  @Override
  public void createPartControl(Composite parent) {
    ZestView zestView = new ZestView();
    zestView.createPartControl(parent);
    addDisposable(zestView);
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}