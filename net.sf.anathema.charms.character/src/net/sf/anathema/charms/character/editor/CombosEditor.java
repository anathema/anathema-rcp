package net.sf.anathema.charms.character.editor;

import net.sf.anathema.basics.item.editor.IEditorControl;
import net.sf.anathema.character.core.editors.AbstractCharacterModelEditorPart;
import net.sf.anathema.charms.character.model.ICharmModel;

public class CombosEditor extends AbstractCharacterModelEditorPart<ICharmModel> {

  public static final String EDITOR_ID = "net.sf.anathema.character.charms.combos.editor"; //$NON-NLS-1$

  @Override
  protected IEditorControl createItemEditorControl() {
    return new CombosEditorControl(this);
  }
}