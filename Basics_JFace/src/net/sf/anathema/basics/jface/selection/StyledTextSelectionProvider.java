package net.sf.anathema.basics.jface.selection;

import net.disy.commons.core.util.IClosure;
import net.sf.anathema.lib.control.GenericControl;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public class StyledTextSelectionProvider implements ISelectionProvider {

  private final StyledText composite;
  private final GenericControl<ISelectionChangedListener> control = new GenericControl<ISelectionChangedListener>();
  private final SelectionListener selectionListener = new SelectionListener() {
    public void widgetSelected(SelectionEvent e) {
      fireSelectionChanged();
    }

    public void widgetDefaultSelected(SelectionEvent e) {
      fireSelectionChanged();
    }
  };

  public StyledTextSelectionProvider(StyledText composite) {
    this.composite = composite;
    this.composite.addSelectionListener(selectionListener);
  }

  private void fireSelectionChanged() {
    final SelectionChangedEvent event = new SelectionChangedEvent(this, getSelection());
    control.forAllDo(new IClosure<ISelectionChangedListener>() {
      public void execute(ISelectionChangedListener input) {
        input.selectionChanged(event);
      }
    });
  }

  public void addSelectionChangedListener(ISelectionChangedListener listener) {
    control.addListener(listener);
  }

  public ISelection getSelection() {
    return new SimpleSelection(composite.getSelectionText().isEmpty());
  }

  public void removeSelectionChangedListener(ISelectionChangedListener listener) {
    control.removeListener(listener);
  }

  public void setSelection(ISelection selection) {
    throw new UnsupportedOperationException();
  }
  
  public void dispose() {
    composite.removeSelectionListener(selectionListener);
  }
}