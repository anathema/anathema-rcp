package net.sf.anathema.character.caste.editor;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.basics.item.editor.AbstractPersistableItemEditorPart;
import net.sf.anathema.basics.item.editor.IEditorControl;
import net.sf.anathema.character.caste.model.ICaste;
import net.sf.anathema.character.caste.model.ICasteModel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class CasteEditor extends AbstractPersistableItemEditorPart<ICasteModel> {

  private final class CasteSelectionListener implements SelectionListener {
    private final Combo combo;

    private CasteSelectionListener(Combo combo) {
      this.combo = combo;
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
      changeCaste();
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
      changeCaste();
    }

    private void changeCaste() {
      int selectionIndex = combo.getSelectionIndex();
      String caste = selectionIndex < 0 ? null : combo.getItem(selectionIndex);
      getPersistableEditorInput().getItem().setCasteByPrintName(caste);
    }
  }

  @Override
  protected IEditorControl createItemEditorControl() {
    return new AbstractItemEditorControl(this) {

      @Override
      public void setFocus() {
        // nothing to do
      }

      @Override
      public void createPartControl(Composite parent) {
        parent.setLayout(new GridLayout(2, false));
        Label label = new Label(parent, SWT.NONE);
        label.setText(Messages.CasteEditor_CasteLabel);
        final Combo combo = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
        ICasteModel item = getPersistableEditorInput().getItem();
        combo.setItems(item.getPrintNameOptions());
        combo.addSelectionListener(new CasteSelectionListener(combo));
        ICaste caste = item.getCaste();
        if (caste != null) {
          String[] items = combo.getItems();
          for (int index = 0; index < items.length; index++) {
            if (items[index].equals(caste.getPrintName())) {
              combo.select(index);
            }
          }
        }
        final CasteEditorInput casteEditorInput = ((CasteEditorInput) getEditorInput());
        casteEditorInput.addModifiableListener(new IChangeListener() {
          @Override
          public void stateChanged() {
            combo.setEnabled(casteEditorInput.isModifiable());
          }
        });
        combo.setEnabled(casteEditorInput.isModifiable());
      }
    };
  }
}