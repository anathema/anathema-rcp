package net.sf.anathema.character.textreport.encoder;

import net.sf.anathema.character.core.character.ICharacter;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;

public interface ITextReportEncoder {

  public Phrase createParagraphs(ICharacter character) throws DocumentException;
}