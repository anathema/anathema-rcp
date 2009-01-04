package net.sf.anathema.character.description.textreport;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.description.ICharacterDescription;
import net.sf.anathema.character.textreport.encoder.AbstractTextEncoder;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;

public class CharacterDescriptionTextEncoder extends AbstractTextEncoder {

  @Override
  public Iterable<Element> createParagraphs(ICharacter character) throws DocumentException {
    List<Element> allPhrases = new ArrayList<Element>();
    ICharacterDescription description = (ICharacterDescription) character.getModel(ICharacterDescription.MODEL_ID);
    allPhrases.add(createNameParagraph(description.getName().getText()));
    if (!StringUtilities.isNullOrEmpty(description.getPeriphrasis().getText())) {
      Paragraph periphrasis = new Paragraph(description.getPeriphrasis().getText(), getUtils().createDefaultFont(
          8,
          Font.ITALIC));
      periphrasis.setAlignment(Element.ALIGN_CENTER);
      allPhrases.add(periphrasis);
    }
    if (StringUtilities.isNullOrEmpty(description.getCharacterization().getText())
        && StringUtilities.isNullOrEmpty(description.getPhysicalDescription().getText())
        && StringUtilities.isNullOrEmpty(description.getNotes().getText())) {
      return allPhrases;
    }
    Phrase descriptionPhrase = new Phrase();
    boolean isFirst = true;
    if (!StringUtilities.isNullOrEmpty(description.getCharacterization().getText())) {
      descriptionPhrase.add(createTextChunk(description.getCharacterization().getText()));
      allPhrases.add(descriptionPhrase);
      isFirst = false;
    }
    if (!StringUtilities.isNullOrEmpty(description.getPhysicalDescription().getText())) {
      Chunk descriptionChunk = createTextChunk(description.getPhysicalDescription().getText());
      addTextualDescriptionPart(allPhrases, descriptionPhrase, isFirst, descriptionChunk);
      isFirst = false;
    }
    if (!StringUtilities.isNullOrEmpty(description.getNotes().getText())) {
      Chunk noteChunk = createTextChunk(description.getNotes().getText());
      addTextualDescriptionPart(allPhrases, descriptionPhrase, isFirst, noteChunk);
    }
    return allPhrases;
  }

  private Paragraph createNameParagraph(String name) {
    Font font = getUtils().createDefaultFont(11, Font.BOLD);
    Paragraph paragraph = new Paragraph(name, font);
    paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
    paragraph.setLeading(font.getSize() * 1.2f);
    return paragraph;
  }

  private void addTextualDescriptionPart(List<Element> allPhrases, Phrase parentPhrase, boolean isFirst, Chunk chunk) {
    if (isFirst) {
      parentPhrase.add(chunk);
      allPhrases.add(parentPhrase);
    }
    else {
      Paragraph descriptionParagraph = createTextParagraph(chunk);
      descriptionParagraph.setFirstLineIndent(5f);
      allPhrases.add(descriptionParagraph);
    }
  }
}