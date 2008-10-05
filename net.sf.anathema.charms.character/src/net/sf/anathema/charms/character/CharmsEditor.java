package net.sf.anathema.charms.character;

import net.sf.anathema.basics.item.editor.AbstractPersistableItemEditorPart;
import net.sf.anathema.basics.item.editor.IEditorControl;

public class CharmsEditor extends AbstractPersistableItemEditorPart<ICharmModel> {

  public static final String EDITOR_ID = "net.sf.anathema.charms.character.editor"; //$NON-NLS-1$

  @Override
  protected IEditorControl createItemEditorControl() {
    return new CharmsEditorControl(this);
  }
}