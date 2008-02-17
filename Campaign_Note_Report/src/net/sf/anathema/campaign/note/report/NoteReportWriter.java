package net.sf.anathema.campaign.note.report;

import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.pdfexport.writer.AbstractReportPdfWriter;
import net.sf.anathema.lib.textualdescription.ITextPart;

import org.eclipse.core.runtime.IProgressMonitor;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.MultiColumnText;
import com.lowagie.text.pdf.PdfWriter;

public class NoteReportWriter extends AbstractReportPdfWriter<ITitledText> {

  private final ITextReportUtils reportUtils = new ITextReportUtils();

  @Override
  protected void performPrint(IProgressMonitor monitor, ITitledText item, Document document, PdfWriter writer)
      throws DocumentException {
    ITextPart[] text = item.getContent().getTextParts();
    document.add(reportUtils.createNewParagraph(item.getName().getText(), Element.ALIGN_CENTER, Font.BOLD));
    MultiColumnText columnText = new MultiColumnText();
    columnText.addRegularColumns(document.left(), document.right(), 10f, 2);
    Paragraph paragraph = new Paragraph();
    paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
    for (ITextPart textpart : text) {
      Font font = reportUtils.createDefaultFont(8, reportUtils.getStyle(textpart.getFormat()));
      paragraph.add(new Chunk(textpart.getText(), font));
    }
    columnText.addElement(paragraph);
    document.add(columnText);
  }

  @Override
  protected String getTitle(ITitledText item) {
    return item.getName().getText();
  }

  @Override
  public int getTaskCount() {
    return 0;
  }
}