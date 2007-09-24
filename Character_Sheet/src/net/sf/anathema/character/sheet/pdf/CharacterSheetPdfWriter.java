package net.sf.anathema.character.sheet.pdf;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.description.ICharacterDescription;
import net.sf.anathema.character.sheet.content.IContentEncoderProvider;
import net.sf.anathema.character.sheet.page.IPdfPageEncoder;
import net.sf.anathema.character.sheet.page.PdfFirstPageEncoder;
import net.sf.anathema.character.sheet.page.PdfPageConfiguration;
import net.sf.anathema.character.sheet.page.PdfSecondPageEncoder;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class CharacterSheetPdfWriter implements ICharacterSheetWriter {
  private final PageSize pageSize = PageSize.A4;
  private final IContentEncoderProvider encoderProvider = new ContentEncoderProvider(
      new RegisteredContentEncoderProvider());

  public void write(ICharacter character, OutputStream outputStream) throws DocumentException {
    Document document = new Document();
    PdfWriter writer = PdfWriter.getInstance(document, outputStream);
    writer.setPdfVersion(PdfWriter.VERSION_1_5);
    writer.setViewerPreferences(PdfWriter.DisplayDocTitle);
    String name = getCharacterName(character);
    document.addTitle(name);
    document.addCreator("Anathema"); //$NON-NLS-1$
    document.open();
    performPrint(character, document, writer);
    document.close();
  }

  private String getCharacterName(ICharacter character) {
    ICharacterDescription model = (ICharacterDescription) character.getModel("net.sf.anathema.character.description.model"); //$NON-NLS-1$
    return model.getName().getText();
  }

  private void performPrint(ICharacter character, Document document, PdfWriter writer) throws DocumentException {
    document.setPageSize(pageSize.getRectangle());
    document.open();
    PdfContentByte directContent = writer.getDirectContent();
    PdfPageConfiguration configuration = PdfPageConfiguration.create(pageSize.getRectangle());
    List<IPdfPageEncoder> encoderList = new ArrayList<IPdfPageEncoder>();
    EncodeContext context = new EncodeContext();
    encoderList.add(new PdfFirstPageEncoder(encoderProvider, configuration, context));
    encoderList.add(new PdfSecondPageEncoder(encoderProvider, configuration, context));
    boolean isFirstPrinted = false;
    for (IPdfPageEncoder encoder : encoderList) {
      if (isFirstPrinted) {
        document.newPage();
      }
      else {
        isFirstPrinted = true;
      }
      encoder.encode(document, directContent, character);
    }
  }
}