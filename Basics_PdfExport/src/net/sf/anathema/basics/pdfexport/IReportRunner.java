package net.sf.anathema.basics.pdfexport;

import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;

public interface IReportRunner {

  public void runWriting(Shell shell, final IEditorPart editorPart, IRunnableContext runnableContext);
}