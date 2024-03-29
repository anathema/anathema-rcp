package net.sf.anathema.basics.pdfexport.writer;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.pdfexport.IPluginConstants;
import net.sf.anathema.basics.pdfexport.IReportRunner;
import net.sf.anathema.basics.swt.file.IOutputStreamFactory;
import net.sf.anathema.basics.swt.file.IStreamResult;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;

public abstract class AbstractReportRunner<I> implements IReportRunner<I> {

  private final Logger logger = new Logger(IPluginConstants.PLUGIN_ID);
  private final IOutputStreamFactory streamFactory;

  public AbstractReportRunner(IOutputStreamFactory streamFactory) {
    this.streamFactory = streamFactory;
  }

  public final void runWriting(Shell shell, IExportItem<I> exportItem, IRunnableContext runnableContext) {
    OutputStream outputStream = null;
    IStreamResult streamResult = null;
    try {
      streamResult = streamFactory.create(shell);
      if (streamResult == null) {
        return;
      }
      outputStream = streamResult.createStream();
      runnableContext.run(true, false, createRunnable(exportItem, outputStream));
      IOUtilities.close(outputStream);
      streamResult.openResult();
    }
    catch (InvocationTargetException e) {
      handleException(shell, outputStream, streamResult, e.getCause());
    }
    catch (Exception e) {
      handleException(shell, outputStream, streamResult, e);
    }
  }

  private void handleException(Shell shell, OutputStream outputStream, IStreamResult streamResult, Throwable exception) {
    if (exception instanceof FileNotFoundException) {
      MessageDialog.openError(shell, Messages.ReportRunner_Title, Messages.ReportRunner_FileInUseMessage);
    }
    else {
      IOUtilities.close(outputStream);
      if (streamResult != null) {
        streamResult.deleteResult();
      }
      logger.error(Messages.ReportRunner_PdfErrorMessage, exception);
      MessageDialog.openError(shell, Messages.ReportRunner_Title, Messages.ReportRunner_PdfErrorMessage);
    }
  }

  protected abstract IRunnableWithProgress createRunnable(IExportItem<I> exportItem, OutputStream outputStream);
}