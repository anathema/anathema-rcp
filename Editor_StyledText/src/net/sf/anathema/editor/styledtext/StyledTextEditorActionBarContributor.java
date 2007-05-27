package net.sf.anathema.editor.styledtext;

import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.textualdescription.TextAspect;

import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.EditorActionBarContributor;

public class StyledTextEditorActionBarContributor extends EditorActionBarContributor {

  private IStyledTextAction[] actions = {
      new TextModificationAction(Messages.StyledTextEditorActionBar_BoldButtonName, new AspectedTextModification(
          TextAspect.Bold)),
      new TextModificationAction(Messages.StyledTextEditorActionBar_ItalicsButtonName, new AspectedTextModification(
          TextAspect.Italic)),
      new TextModificationAction(Messages.StyledTextEditorActionBar_UnderlineButtonName, new AspectedTextModification(
          TextAspect.Underline)) };
  private final ISelectionChangedListener selectionListener = new ISelectionChangedListener() {
    public void selectionChanged(SelectionChangedEvent event) {
      updateState();
    }
  };
  private final IChangeListener caretChangeListener = new IChangeListener() {
    @Override
    public void changeOccured() {
      updateState();
    }
  };
  private IStyledTextEditor targetEditor;

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
      this.targetEditor.removeCaretChangeListener(caretChangeListener);
    }
    this.targetEditor = (IStyledTextEditor) targetEditor;
    if (this.targetEditor != null) {
      this.targetEditor.getEditorSite().getSelectionProvider().addSelectionChangedListener(selectionListener);
      this.targetEditor.addCaretChangeListener(caretChangeListener);
    }
    for (IStyledTextAction action : actions) {
      action.setEditor(this.targetEditor);
    }
  }
}