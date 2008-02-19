package net.sf.anathema.basics.pdfexport;

import net.sf.anathema.basics.pdfexport.item.IExportItem;

import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;

public interface IReportRunner<I> {

  public void runWriting(
      Shell shell,
      IExportItem<I> exportItem,
      final IEditorPart editorPart,
      IRunnableContext runnableContext);
}