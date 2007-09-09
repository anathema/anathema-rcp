package net.sf.anathema.character.attributes.view;

import net.sf.anathema.character.attributes.AttributesPlugin;
import net.sf.anathema.character.trait.groupeditor.TraitGroupEditor;

public class MarkBonusPointsAction extends org.eclipse.jface.action.Action {

  private TraitGroupEditor editor;

  public MarkBonusPointsAction() {
    setToolTipText(Messages.MarkBonusPointsAction_Tooltip);
    setImageDescriptor(AttributesPlugin.getDefaultInstance().getImageDescriptor("icons/highlight.gif")); //$NON-NLS-1$
  }

  public void setEditor(TraitGroupEditor editor) {
    this.editor = editor;
  }

  @Override
  public void run() {
    editor.markBonusPoints();
  }
}