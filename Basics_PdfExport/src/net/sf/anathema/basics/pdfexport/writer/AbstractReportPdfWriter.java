package net.sf.anathema.basics.pdfexport.writer;

import java.io.OutputStream;

import net.sf.anathema.basics.pdfexport.PageSize;

import org.eclipse.core.runtime.IProgressMonitor;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;

public abstract class AbstractReportPdfWriter<I> implements IReportWriter<I> {

  protected final PageSize pageSize = PageSize.A4;

  protected abstract void performPrint(
      IProgressMonitor monitor,
      I item,
      Document document,
      PdfWriter writer) throws DocumentException;

  @Override
  public final void write(IProgressMonitor monitor, I item, OutputStream outputStream) throws DocumentException {
    Document document = new Document();
    PdfWriter writer = PdfWriter.getInstance(document, outputStream);
    writer.setPdfVersion(PdfWriter.VERSION_1_5);
    writer.setViewerPreferences(PdfWriter.DisplayDocTitle);
    document.addTitle(getTitle(item));
    document.addCreator("Anathema"); //$NON-NLS-1$
    document.setPageSize(pageSize.getRectangle());
    document.open();
    performPrint(monitor, item, document, writer);
    document.close();
  }

  protected abstract String getTitle(I item);
}