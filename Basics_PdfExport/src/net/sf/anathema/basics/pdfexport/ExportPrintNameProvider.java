package net.sf.anathema.basics.pdfexport;

import net.disy.commons.core.model.ObjectModel;
import net.sf.anathema.basics.eclipse.runtime.IProvider;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;

public final class ExportPrintNameProvider<T> implements IProvider<String> {
  private final ObjectModel<IExportItem<T>> selectedItem;

  public ExportPrintNameProvider(ObjectModel<IExportItem<T>> selectedItem) {
    this.selectedItem = selectedItem;
  }

  @Override
  public String get() {
    IExportItem<T> value = selectedItem.getValue();
    return value == null ? null : value.getPrintName();
  }
}