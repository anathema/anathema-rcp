package net.sf.anathema.character.report.wizard;

import java.io.OutputStream;

import org.eclipse.core.runtime.IProgressMonitor;

import com.lowagie.text.DocumentException;

public interface IReportWriter<I> {

  public void write(IProgressMonitor monitor, I item, OutputStream outputStream) throws DocumentException;

  public int getTaskCount();
}