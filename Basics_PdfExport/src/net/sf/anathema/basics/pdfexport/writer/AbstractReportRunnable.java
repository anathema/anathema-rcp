package net.sf.anathema.basics.pdfexport.writer;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

import com.lowagie.text.DocumentException;

public abstract class AbstractReportRunnable<I> implements IRunnableWithProgress {

  private final OutputStream outputStream;
  private final IReportWriter<I> writer;

  public AbstractReportRunnable(OutputStream outputStream, IReportWriter<I> writer) {
    this.outputStream = outputStream;
    this.writer = writer;
  }

  @Override
  public final void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
    try {
      monitor.beginTask(Messages.AbstractReportRunnable_taskName, 1 + writer.getTaskCount());
      monitor.subTask(Messages.AbstractReportRunnable_preparingItemSubtask);
      I item = createItem();
      monitor.worked(1);
      writer.write(monitor, item, outputStream);
    }
    catch (DocumentException e) {
      throw new InvocationTargetException(e);
    }
    finally {
      monitor.done();
    }
  }

  protected abstract I createItem();
}