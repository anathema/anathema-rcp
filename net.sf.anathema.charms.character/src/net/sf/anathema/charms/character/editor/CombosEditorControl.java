package net.sf.anathema.charms.character.editor;

import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.character.experience.IExperience;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class CombosEditorControl extends AbstractItemEditorControl {

  private final IExperience experience;

  public CombosEditorControl(CombosEditor combosEditor, IExperience experience) {
    super(combosEditor);
    this.experience = experience;
  }

  @Override
  protected void createPartControl(FormToolkit toolkit, Composite body) {
    // nothing to do
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}