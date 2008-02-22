package net.sf.anathema.basics.pdfexport.item;

import net.disy.commons.core.model.ObjectModel;
import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
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

  private final class ExportItemProvider implements IStructuredContentProvider {
    @Override
    public Object[] getElements(Object inputElement) {
      return exportItems.toArray(new Object[exportItems.size()]);
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
    IContentProvider provider = new ExportItemProvider();
    viewer.setContentProvider(provider);
    IBaseLabelProvider labelProvider = new LabelProvider() {
      @Override
      public String getText(Object element) {
        return ((IExportItem< ? >) element).getPrintName();
      }
    };
    viewer.setLabelProvider(labelProvider);
    viewer.setInput(exportItems);
    viewer.addSelectionChangedListener(new ISelectionChangedListener() {
      @Override
      public void selectionChanged(SelectionChangedEvent event) {
        IExportItem<T> selection = (IExportItem<T>) ((IStructuredSelection) event.getSelection()).getFirstElement();
        selectedItem.setValue(selection);
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
}