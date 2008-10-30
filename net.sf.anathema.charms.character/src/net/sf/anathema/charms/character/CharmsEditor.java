package net.sf.anathema.charms.character;

import net.sf.anathema.basics.item.editor.IEditorControl;
import net.sf.anathema.character.core.editors.AbstractCharacterModelEditorPart;

public class CharmsEditor extends AbstractCharacterModelEditorPart<ICharmModel> {

  public static final String EDITOR_ID = "net.sf.anathema.charms.character.editor"; //$NON-NLS-1$

  @Override
  protected IEditorControl createItemEditorControl() {
    return new CharmsEditorControl(this);
  }
}