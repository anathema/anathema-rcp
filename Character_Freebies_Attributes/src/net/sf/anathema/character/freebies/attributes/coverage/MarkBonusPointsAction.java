package net.sf.anathema.character.freebies.attributes.coverage;

import net.sf.anathema.character.trait.groupeditor.TraitGroupEditor;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

public class MarkBonusPointsAction extends Action {

  private TraitGroupEditor editor;

  public MarkBonusPointsAction() {
    super("", IAction.AS_CHECK_BOX); //$NON-NLS-1$
    // TODO Revival der Action mit Inhalten füllen
//    setToolTipText(Messages.MarkBonusPointsAction_Tooltip);
//    setImageDescriptor(AttributesPlugin.getDefaultInstance().getImageDescriptor("icons/highlight.gif")); //$NON-NLS-1$
  }

  public void setEditor(TraitGroupEditor editor) {
    this.editor = editor;
    run();
  }

  @Override
  public void run() {
    editor.getDecoration(SurplusMarkingEditorDecoration.class).markBonusPoints(isChecked());
  }
}