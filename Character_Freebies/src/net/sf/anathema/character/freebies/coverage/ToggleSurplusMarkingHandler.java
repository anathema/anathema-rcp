package net.sf.anathema.character.freebies.coverage;

import net.sf.anathema.character.trait.groupeditor.TraitGroupEditor;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;

public class ToggleSurplusMarkingHandler extends AbstractHandler {

  public static boolean ACTIVE = false;

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    ACTIVE = !ACTIVE;
    TraitGroupEditor editor = (TraitGroupEditor) HandlerUtil.getActiveEditorChecked(event);
    editor.updateDecorations();
    return null;
  }
}