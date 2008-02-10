package net.sf.anathema.character.report.wizard;

import java.io.OutputStream;

import net.sf.anathema.character.core.character.ICharacter;

import org.eclipse.core.runtime.IProgressMonitor;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;

public abstract class AbstractReportPdfWriter implements ICharacterReportWriter {

  protected final PageSize pageSize = PageSize.A4;

  protected abstract void performPrint(
      IProgressMonitor monitor,
      ICharacter character,
      Document document,
      PdfWriter writer) throws DocumentException;

  @Override
  public final void write(IProgressMonitor monitor, ICharacter character, OutputStream outputStream) throws DocumentException {
    Document document = new Document();
    PdfWriter writer = PdfWriter.getInstance(document, outputStream);
    writer.setPdfVersion(PdfWriter.VERSION_1_5);
    writer.setViewerPreferences(PdfWriter.DisplayDocTitle);
    String name = getCharacterName(character);
    document.addTitle(name);
    document.addCreator("Anathema"); //$NON-NLS-1$
    document.setPageSize(pageSize.getRectangle());
    document.open();
    performPrint(monitor, character, document, writer);
    document.close();
  }

  private String getCharacterName(ICharacter character) {
    return "Horst";
//    ICharacterDescription model = (ICharacterDescription) character.getModel("net.sf.anathema.character.description.model"); //$NON-NLS-1$
//    return model.getName().getText();
  }
}