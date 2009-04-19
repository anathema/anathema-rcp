package net.sf.anathema.character.freebies.coverage;

import net.sf.anathema.character.trait.groupeditor.TraitGroupEditor;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.State;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;

public class ToggleSurplusMarkingHandler extends AbstractHandler {

  public static boolean isMarkingActive() {
    State state = getState();
    Object stateValue = state.getValue();
    return (Boolean) stateValue;
  }

  @SuppressWarnings("nls")
  private static State getState() {
    ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
    Command command = service.getCommand("net.sf.anathema.character.freebies.toggle.surplusmarking");
    return command.getState("surplusmarking.active");
  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    toggleState();
    TraitGroupEditor editor = (TraitGroupEditor) HandlerUtil.getActiveEditorChecked(event);
    editor.updateDecorations();
    return null;
  }

  private void toggleState() {
    State state = getState();
    state.setValue(!(Boolean) state.getValue());
  }
}