package net.sf.anathema.basics.importexport.control;

import net.disy.commons.core.model.BooleanModel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class OpenCheckBoxFactory {

  private final String label;
  private final BooleanModel openModel;

  public OpenCheckBoxFactory(String label, BooleanModel openModel) {
    this.label = label;
    this.openModel = openModel;
  }

  public void create(Composite composite) {
    final Button checkBox = new Button(composite, SWT.CHECK);
    GridData gridData = new GridData();
    gridData.horizontalSpan = 3;
    checkBox.setLayoutData(gridData);
    checkBox.setText(label);
    checkBox.addSelectionListener(new SelectionListener() {
      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        updateModel();
      }

      @Override
      public void widgetSelected(SelectionEvent e) {
        updateModel();
      }

      private void updateModel() {
        openModel.setValue(checkBox.getSelection());
      }
    });
    checkBox.setSelection(openModel.getValue());
  }
}