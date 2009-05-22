package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.basics.item.editor.AbstractPersistableItemFormEditorPart;
import net.sf.anathema.basics.item.editor.IEditorControl;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class TraitGroupEditor extends AbstractPersistableItemFormEditorPart<IItem> {

  private GroupEditor groupEditor;

  @Override
  protected IEditorControl createItemEditorControl() {
    return new AbstractItemEditorControl(this) {

      @Override
      public void setFocus() {
        // nothing to do
      }

      @Override
      protected void createPartControl(FormToolkit toolkit, Composite body) {
        throw new UnsupportedOperationException();
      }
    };
  }

  public void updateDecorations() {
    groupEditor.updateDecorations();
  }

  @Override
  protected void addPages() {
    try {
      groupEditor = new GroupEditor();
      setPageText(addPage(groupEditor, getEditorInput()), "Groups");
    }
    catch (PartInitException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}