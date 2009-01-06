package net.sf.anathema.character.caste.textreport;

import java.util.Collections;

import net.sf.anathema.character.caste.ICaste;
import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.textreport.encoder.AbstractTextEncoder;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;

public class CasteTextReportEncoder extends AbstractTextEncoder {

  @Override
  public Iterable<Element> createParagraphs(ICharacter character) throws DocumentException {
    Phrase castePhrase = null;
    ICasteModel model = (ICasteModel) character.getModel(ICasteModel.ID);
    if (model != null) {
      ICaste caste = model.getCaste();
      if (caste != null) {
        castePhrase = createTextParagraph(createBoldTitle(Messages.CasteTextReportEncoder_CasteLabel + " ")); //$NON-NLS-1$
        castePhrase.add(createTextChunk(caste.getPrintName()));
      }
    }
    if (castePhrase == null) {
      return Collections.emptyList();
    }
    return Collections.singletonList((Element) castePhrase);
  }
}