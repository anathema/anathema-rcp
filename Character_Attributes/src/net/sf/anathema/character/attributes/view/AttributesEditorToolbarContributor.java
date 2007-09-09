package net.sf.anathema.character.attributes.view;

import net.sf.anathema.character.trait.groupeditor.TraitGroupEditor;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.EditorActionBarContributor;

public class AttributesEditorToolbarContributor extends EditorActionBarContributor {

  private final MarkBonusPointsAction action = new MarkBonusPointsAction();

  @Override
  public void contributeToToolBar(IToolBarManager toolBarManager) {
    toolBarManager.add(action);
  }

  @Override
  public void setActiveEditor(IEditorPart targetEditor) {
    action.setEditor((TraitGroupEditor) targetEditor);
  }
}