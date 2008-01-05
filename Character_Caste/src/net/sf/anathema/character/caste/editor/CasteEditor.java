package net.sf.anathema.character.caste.editor;

import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.basics.item.editor.AbstractPersistableItemEditorPart;
import net.sf.anathema.basics.item.editor.IEditorControl;
import net.sf.anathema.character.caste.model.ICasteModel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class CasteEditor extends AbstractPersistableItemEditorPart<ICasteModel> {

  @Override
  protected IEditorControl createItemEditorControl() {
    return new AbstractItemEditorControl(this) {
    
      @Override
      public void setFocus() {
        // nothing to do
      }
    
      @Override
      public void createPartControl(Composite parent) {
        new Label(parent, SWT.NONE).setText("Hasä an Bord");
      }
    };
  }
}