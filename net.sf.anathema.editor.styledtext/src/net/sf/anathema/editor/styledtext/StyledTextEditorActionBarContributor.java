package net.sf.anathema.editor.styledtext;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.lib.textualdescription.TextAspect;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.EditorActionBarContributor;

public class StyledTextEditorActionBarContributor extends EditorActionBarContributor {

  private final IStyledTextAction[] actions = {
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
    public void stateChanged() {
      updateState();
    }
  };
  private IStyledTextEditor targetEditor;

  @Override
  public void contributeToToolBar(IToolBarManager manager) {
    for (IStyledTextAction action : actions) {
      manager.add(action);
    }
  }

  private void updateState() {
    for (IStyledTextAction action : actions) {
      action.updateState();
    }
  }

  @Override
  public void setActiveEditor(IEditorPart newEditor) {
    disposeTargetEditor();
    if (newEditor != null && newEditor.getEditorSite().getSelectionProvider() != null) {
      initializeTargetEditor(newEditor);
    }
    else {
      this.targetEditor = null;
    }
    for (IStyledTextAction action : actions) {
      action.setEditor(this.targetEditor);
    }
    updateState();
  }

  private void initializeTargetEditor(IEditorPart editor) {
    this.targetEditor = (IStyledTextEditor) editor;
    ISelectionProvider selectionProvider = editor.getEditorSite().getSelectionProvider();
    selectionProvider.addSelectionChangedListener(selectionListener);
    this.targetEditor.addCaretChangeListener(caretChangeListener);
  }

  private void disposeTargetEditor() {
    if (this.targetEditor != null) {
      ISelectionProvider selectionProvider = this.targetEditor.getEditorSite().getSelectionProvider();
      selectionProvider.removeSelectionChangedListener(selectionListener);
      this.targetEditor.removeCaretChangeListener(caretChangeListener);
    }
  }
}