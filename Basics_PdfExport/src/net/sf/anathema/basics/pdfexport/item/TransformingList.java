package net.sf.anathema.basics.pdfexport.item;

import net.disy.commons.core.model.ObjectModel;
import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

public class TransformingList<T> {

  private final class ListSelectionListener implements SelectionListener {
    private final List itemList;

    private ListSelectionListener(List itemList) {
      this.itemList = itemList;
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
      updateSelection();
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
      updateSelection();
    }

    private void updateSelection() {
      int selectionIndex = itemList.getSelectionIndex();
      if (selectionIndex > -1) {
        selectionModel.setValue(items[selectionIndex]);
      }
      else {
        selectionModel.setValue(null);
      }
    }
  }

  private final T[] items;
  private final ITransformer<T, String> transformer;
  private final ObjectModel<T> selectionModel;

  public TransformingList(T[] items, ITransformer<T, String> transformer, ObjectModel<T> selectionModel) {
    this.items = items;
    this.transformer = transformer;
    this.selectionModel = selectionModel;
  }

  public List createList(Composite parent) {
    final List itemList = new List(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
    itemList.setItems(ArrayUtilities.transform(items, String.class, transformer));
    itemList.addSelectionListener(new ListSelectionListener(itemList));
    return itemList;
  }
}