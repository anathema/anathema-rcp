package net.sf.anathema.basics.pdfexport.item;

import java.util.ArrayList;

import net.disy.commons.core.model.ObjectModel;
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
        selectionModel.setValue(items.get(selectionIndex));
      }
      else {
        selectionModel.setValue(null);
      }
    }
  }

  private final java.util.List<T> items;
  private final ITransformer<T, String> transformer;
  private final ObjectModel<T> selectionModel;

  public TransformingList(java.util.List<T> items, ITransformer<T, String> transformer, ObjectModel<T> selectionModel) {
    this.items = items;
    this.transformer = transformer;
    this.selectionModel = selectionModel;
  }

  public List createList(Composite parent) {
    final List itemList = new List(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
    java.util.List<String> stringPresentation = new ArrayList<String>();
    for (T item : items) {
      stringPresentation.add(transformer.transform(item));
    }
    itemList.setItems(stringPresentation.toArray(new String[stringPresentation.size()]));
    itemList.addSelectionListener(new ListSelectionListener(itemList));
    return itemList;
  }
}