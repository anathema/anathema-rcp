package editor.styledtext.editors;

import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.EditorActionBarContributor;

public class StyledTextEditorActionBarContributor extends EditorActionBarContributor {

  private IStyledTextAction[] actions = {
      new TextModificationAction("B", new BoldModification()),
      new TextModificationAction("I", new ItalicsModification()),
      new TextModificationAction("U", new UnderlineModification()) };
  private final ISelectionChangedListener selectionListener = new ISelectionChangedListener() {
    public void selectionChanged(SelectionChangedEvent event) {
      updateState();
    }
  };
  private IEditorPart targetEditor;

  @Override
  public void contributeToToolBar(IToolBarManager toolBarManager) {
    addToContributionManager(toolBarManager);
  }

  private void updateState() {
    for (IStyledTextAction action : actions) {
      action.updateState();
    }
  }

  private void addToContributionManager(IContributionManager manager) {
    for (IStyledTextAction action : actions) {
      manager.add(action);
    }
  }

  @Override
  public void setActiveEditor(IEditorPart targetEditor) {
    if (this.targetEditor != null) {
      this.targetEditor.getEditorSite().getSelectionProvider().removeSelectionChangedListener(selectionListener);
    }
    this.targetEditor = targetEditor;
    if (this.targetEditor != null) {
      this.targetEditor.getEditorSite().getSelectionProvider().addSelectionChangedListener(selectionListener);
    }
    for (IStyledTextAction action : actions) {
      action.setEditor((IStyledTextEditor) targetEditor);
    }
  }
}