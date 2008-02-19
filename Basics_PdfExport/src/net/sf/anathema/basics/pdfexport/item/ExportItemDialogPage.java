package net.sf.anathema.basics.pdfexport.item;

import net.disy.commons.core.model.ObjectModel;
import net.disy.commons.core.model.listener.IChangeListener;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

public class ExportItemDialogPage<T> extends WizardPage {

  public static final class ExportItemToPrintNameTransformer<T> implements ITransformer<IExportItem<T>, String> {
    @Override
    public String transform(IExportItem<T> exportItem) {
      return exportItem.getPrintName();
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
    ExportItemToPrintNameTransformer<T> transformer = new ExportItemToPrintNameTransformer<T>();
    TransformingList<IExportItem<T>> list = new TransformingList<IExportItem<T>>(exportItems, transformer, selectedItem);
    setControl(list.createList(parent));
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