package net.sf.anathema.basics.pdfexport.item;

import net.disy.commons.core.model.ObjectModel;
import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

public class ExportItemDialogPage<T> extends WizardPage {

  private static final class ExportItemLabelProvider extends LabelProvider {
    @Override
    public String getText(Object element) {
      return ((IExportItem< ? >) element).getPrintName();
    }
  }

  private static final class ExportItemProvider implements IStructuredContentProvider {
    @SuppressWarnings("unchecked")
    @Override
    public Object[] getElements(Object inputElement) {
      java.util.List<IExportItem< ? >> items = (java.util.List<IExportItem< ? >>) inputElement;
      return items.toArray(new Object[items.size()]);
    }

    @Override
    public void dispose() {
      // nothing to do
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
      // nothing to do
    }
  }

  private final java.util.List<IExportItem<T>> exportItems;
  private final ObjectModel<IExportItem<T>> selectedItem;

  public ExportItemDialogPage(java.util.List<IExportItem<T>> exportItems, ObjectModel<IExportItem<T>> selectedItem) {
    super("ExportItem"); //$NON-NLS-1$
    this.exportItems = exportItems;
    this.selectedItem = selectedItem;
    setDescription("Please select the item you want to export.");
    setTitle("Export item selection");
    selectedItem.addChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        getContainer().updateButtons();
      }
    });
  }

  @Override
  public void createControl(Composite parent) {
    ListViewer viewer = new ListViewer(new List(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER));
    viewer.setContentProvider(new ExportItemProvider());
    viewer.setLabelProvider(new ExportItemLabelProvider());
    viewer.setInput(exportItems);
    viewer.addSelectionChangedListener(new ISelectionChangedListener() {
      @Override
      public void selectionChanged(SelectionChangedEvent event) {
        setSelection(event.getSelection());
      }

    });
    viewer.addDoubleClickListener(new IDoubleClickListener() {
      @Override
      public void doubleClick(DoubleClickEvent event) {
        ISelection selection = event.getSelection();
        setSelection(selection);
        if (!selection.isEmpty()) {
          getContainer().showPage(getNextPage());
        }
      }
    });
    IExportItem<T> item = selectedItem.getValue();
    if (item != null) {
      viewer.setSelection(new StructuredSelection(item));
    }
    setControl(viewer.getControl());
  }

  @Override
  public boolean canFlipToNextPage() {
    return selectedItem.getValue() != null;
  }

  @Override
  public boolean isPageComplete() {
    return canFlipToNextPage();
  }

  @SuppressWarnings("unchecked")
  private void setSelection(ISelection selection) {
    selectedItem.setValue((IExportItem<T>) ((IStructuredSelection) selection).getFirstElement());
  }
}