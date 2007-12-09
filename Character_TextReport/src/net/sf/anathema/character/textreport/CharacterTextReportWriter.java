package net.sf.anathema.character.textreport;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.report.pdf.AbstractReportPdfWriter;
import net.sf.anathema.character.textreport.encoder.ITextReportEncoder;

import org.eclipse.core.runtime.IProgressMonitor;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.MultiColumnText;
import com.lowagie.text.pdf.PdfWriter;

public class CharacterTextReportWriter extends AbstractReportPdfWriter {

  private final Iterable<ITextReportEncoder> encoders;

  public CharacterTextReportWriter(Iterable<ITextReportEncoder> encoders) {
    this.encoders = encoders;
  }

  @Override
  public int getTaskCount() {
    return 0;
  }

  @Override
  protected void performPrint(IProgressMonitor monitor, ICharacter character, Document document, PdfWriter writer)
      throws DocumentException {
    MultiColumnText columnText = new MultiColumnText(document.top() - document.bottom() - 15);
    columnText.addRegularColumns(document.left(), document.right(), 20, 2);
    try {
      for (ITextReportEncoder encoder : encoders) {
        addPhrase(columnText, encoder.createParagraphs(character));
      }
      writeColumnText(document, columnText);
    }
    catch (DocumentException e) {
      e.printStackTrace();
    }
  }

  protected void addPhrase(MultiColumnText columnText, Phrase phrase) throws DocumentException {
    columnText.addElement(phrase);
  }

  private void writeColumnText(Document document, MultiColumnText columnText) throws DocumentException {
    do {
      document.add(columnText);
      columnText.nextColumn();
    }
    while (columnText.isOverflow());
  }
}