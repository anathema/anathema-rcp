package net.sf.anathema.basics.pdfexport.item;

import net.disy.commons.core.model.ObjectModel;
import net.disy.commons.core.model.listener.IChangeListener;
import net.disy.commons.core.util.ITransformer;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

public class ExportItemDialogPage extends WizardPage {

  public static final class ExportItemToPrintNameTransformer implements ITransformer<IExportItem, String> {
    @Override
    public String transform(IExportItem exportItem) {
      return exportItem.getPrintName();
    }
  }

  private final IExportItem[] exportItems;
  private final ObjectModel<IExportItem> selectedItem;

  public ExportItemDialogPage(IExportItem[] exportItems, ObjectModel<IExportItem> selectedItem) {
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
    ExportItemToPrintNameTransformer transformer = new ExportItemToPrintNameTransformer();
    TransformingList<IExportItem> list = new TransformingList<IExportItem>(exportItems, transformer, selectedItem);
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