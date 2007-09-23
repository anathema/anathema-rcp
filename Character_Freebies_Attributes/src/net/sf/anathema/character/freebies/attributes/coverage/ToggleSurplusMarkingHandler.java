package net.sf.anathema.character.freebies.attributes.coverage;

import net.sf.anathema.character.trait.groupeditor.TraitGroupEditor;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;

public class ToggleSurplusMarkingHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    TraitGroupEditor editor = (TraitGroupEditor) HandlerUtil.getActiveEditorChecked(event);
    editor.getDecoration(SurplusMarkingEditorDecoration.class).toggleMarkBonusPoints();
    return null;
  }
}