package net.sf.anathema.character.report.pdf;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

public abstract class AbstractReportHandler extends AbstractHandler {

  protected abstract ICharacterReportWriter createReportWriter();

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    Shell shell = HandlerUtil.getActiveShell(event);
    IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
    IRunnableContext runnableContext = HandlerUtil.getActiveWorkbenchWindow(event);
    ICharacterReportWriter reportWriter = createReportWriter();
    new ReportExporter().export(shell, editorPart, runnableContext, reportWriter);
    return null;
  }
}