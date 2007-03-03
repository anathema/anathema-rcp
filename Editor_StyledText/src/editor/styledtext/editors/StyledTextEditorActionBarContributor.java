package editor.styledtext.editors;

import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.EditorActionBarContributor;

public class StyledTextEditorActionBarContributor extends EditorActionBarContributor {

  private IStyledTextAction[] actions = { new TextModificationAction("B", new BoldModification()) };

  @Override
  public void contributeToToolBar(IToolBarManager toolBarManager) {
    addToContributionManager(toolBarManager);
  }

  private void addToContributionManager(IContributionManager manager) {
    for (IStyledTextAction action : actions) {
      manager.add(action);
    }
  }

  @Override
  public void setActiveEditor(IEditorPart targetEditor) {
    for (IStyledTextAction action : actions) {
      action.setEditor((IStyledTextEditor) targetEditor);
    }
  }
}