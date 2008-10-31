package net.sf.anathema.campaign.plot.report;

import java.util.HashMap;

import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.pdfexport.itext.ITextReportUtils;
import net.sf.anathema.basics.pdfexport.writer.AbstractReportPdfWriter;
import net.sf.anathema.campaign.plot.report.model.IPlotElement;
import net.sf.anathema.campaign.plot.report.toc.TableOfContentsPrinter;
import net.sf.anathema.campaign.plot.report.util.SeriesReportUtils;
import net.sf.anathema.lib.textualdescription.ITextPart;

import org.eclipse.core.runtime.IProgressMonitor;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.TextElementArray;
import com.lowagie.text.pdf.MultiColumnText;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public class MultiColumnSeriesReportWriter extends AbstractReportPdfWriter<IPlotElement> {

  private final ITextReportUtils reportUtils = new ITextReportUtils();
  private final SeriesReportUtils seriesUtils = new SeriesReportUtils();
  private final TableOfContentsPrinter contentTable = new TableOfContentsPrinter();

  @Override
  protected void performPrint(IProgressMonitor monitor, IPlotElement item, Document document, PdfWriter writer)
      throws DocumentException {
    contentTable.reset();
    writer.setSpaceCharRatio(PdfWriter.NO_SPACE_CHAR_RATIO);
    writer.setViewerPreferences(PdfWriter.PageModeUseOutlines);
    writer.setLinearPageMode();
    writer.setPageEvent(new PdfPageEventHelper() {
      @Override
      public void onGenericTag(PdfWriter currentWriter, Document currentDocument, Rectangle rect, String text) {
        contentTable.addEntry(text, currentWriter.getPageNumber());
      }

      @Override
      public void onEndPage(PdfWriter currentWriter, Document currentDocument) {
        seriesUtils.printPageNumber(currentWriter, currentDocument, String.valueOf(currentWriter.getPageNumber()));
      }
    });
    PdfOutline rootOutline = writer.getDirectContent().getRootOutline();
    String seriesTitle = item.getContent().getName().getText();
    new PdfOutline(rootOutline, new PdfAction(PdfAction.FIRSTPAGE), Messages.MultiColumnSeriesReport_TableOfContent);
    document.newPage();
    Paragraph synopsisParagraph = createTitleParagraph(Messages.MultiColumnSeriesReport_Synopsis, 13);
    document.add(synopsisParagraph);
    addOutline(rootOutline, Messages.MultiColumnSeriesReport_Synopsis);
    MultiColumnText synopsisColumnText = new MultiColumnText(document.top() - document.bottom() - 15);
    synopsisColumnText.addRegularColumns(document.left(), document.right(), 20, 2);
    synopsisColumnText.addElement(createContentParagraph(item.getContent()));
    writeColumnText(document, synopsisColumnText);

    int storyNumber = 1;
    for (IPlotElement story : item.getChildren()) {
      document.newPage();
      String storyTitle = createSectionTitle(story.getContent(), new int[] { storyNumber });
      Paragraph storyTitleParagraph = createTitleParagraph(storyTitle, 13);
      document.add(storyTitleParagraph);
      PdfOutline storyOutline = addOutline(rootOutline, storyTitle);
      MultiColumnText columnText = new MultiColumnText(document.top() - document.bottom() - 15);
      columnText.addRegularColumns(document.left(), document.right(), 20, 2);
      addTextAndChildren(columnText, story, storyOutline, new int[] { storyNumber++ });
      writeColumnText(document, columnText);
    }
    contentTable.performPrint(seriesTitle, Messages.MultiColumnSeriesReport_TableOfContent, document, writer);
  }

  private void writeColumnText(Document document, MultiColumnText columnText) throws DocumentException {
    do {
      document.add(columnText);
      columnText.nextColumn();
    }
    while (columnText.isOverflow());
  }

  private void addTextAndChildren(
      MultiColumnText columnText,
      IPlotElement element,
      PdfOutline elementOutline,
      int[] elementTitleNumbers) throws DocumentException {
    columnText.addElement(createContentParagraph(element.getContent()));
    int episodeNumber = 1;
    for (IPlotElement episode : element.getChildren()) {
      addSubElement(columnText, episode, elementOutline, elementTitleNumbers, episodeNumber++);
    }
  }

  private void addSubElement(
      MultiColumnText columnText,
      IPlotElement element,
      PdfOutline parentOutline,
      int[] superElementNumbers,
      int thisElementNumber) throws DocumentException {
    int[] titleNumbers = extendArray(superElementNumbers, thisElementNumber);
    String elementTitle = createSectionTitle(element.getContent(), titleNumbers);
    Paragraph elementTitleParagraph = createTitleParagraph(elementTitle, 9);
    PdfOutline outline = addOutline(parentOutline, elementTitle);
    columnText.addElement(elementTitleParagraph);
    addTextAndChildren(columnText, element, outline, titleNumbers);
  }

  private int[] extendArray(int[] array, int extension) {
    int[] newArray = new int[array.length + 1];
    System.arraycopy(array, 0, newArray, 0, array.length);
    newArray[array.length] = extension;
    return newArray;
  }

  private PdfOutline addOutline(PdfOutline parentOutline, String outlineTitle) {
    PdfAction episodeAction = PdfAction.gotoLocalPage(outlineTitle, false);
    return new PdfOutline(parentOutline, episodeAction, outlineTitle);
  }

  private Paragraph createTitleParagraph(String titleString, int headerSize) {
    Font font = reportUtils.createDefaultFont(headerSize, Font.BOLD);
    Chunk title = new Chunk(titleString, font);
    title.setLocalDestination(titleString);
    getAttributeMap(title).put(Chunk.GENERICTAG, titleString);
    Paragraph paragraph = new Paragraph(title);
    paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
    paragraph.setLeading(font.getSize() * 1.2f);
    return paragraph;
  }

  @SuppressWarnings("unchecked")
  private HashMap<Object, Object> getAttributeMap(Chunk title) {
    return title.getAttributes();
  }

  private String createSectionTitle(ITitledText description, int[] sectionMarking) {
    String prepend = new String();
    for (int mark : sectionMarking) {
      prepend = prepend.concat(mark + "."); //$NON-NLS-1$
    }
    String finalTitle = prepend + " " + description.getName().getText(); //$NON-NLS-1$
    return finalTitle;
  }

  private TextElementArray createContentParagraph(ITitledText description) {
    ITextPart[] content = description.getContent().getTextParts();
    Paragraph contentParagraph = new Paragraph();
    contentParagraph.setLeading(0, 1.4f);
    contentParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
    for (ITextPart textpart : content) {
      Font font = reportUtils.createDefaultFont(8, reportUtils.getStyle(textpart.getFormat()));
      contentParagraph.add(new Chunk(textpart.getText(), font));
    }
    if (content.length > 0) {
      contentParagraph.setSpacingAfter(12);
    }
    return contentParagraph;
  }

  @Override
  protected String getTitle(IPlotElement item) {
    return item.getContent().getName().getText();
  }

  @Override
  public int getTaskCount() {
    return 0;
  }
}