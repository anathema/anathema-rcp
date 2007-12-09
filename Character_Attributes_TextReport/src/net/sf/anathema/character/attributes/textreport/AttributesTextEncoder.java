package net.sf.anathema.character.attributes.textreport;

import java.util.List;

import net.sf.anathema.character.attributes.model.AttributeMessages;
import net.sf.anathema.character.attributes.util.AttributeDisplayUtilties;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.textreport.encoder.ITextReportEncoder;
import net.sf.anathema.character.textreport.util.TextReportUtils;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;

public abstract class AttributesTextEncoder extends AbstractTextEncoder implements ITextReportEncoder {

  public AttributesTextEncoder(TextReportUtils utils) {
    super(utils);
  }

  public Phrase createParagraphs(ICharacter character) throws DocumentException {
    Phrase traitPhrase = createTextParagraph(createBoldTitle("Attributes:"));
    traitPhrase.add(createBoldTitle(" ")); //$NON-NLS-1$
    List<IDisplayTraitGroup<IDisplayTrait>> groups = AttributeDisplayUtilties.getDisplayAttributeGroups(character);
    boolean firstPrinted = true;
    for (IDisplayTraitGroup<IDisplayTrait> group: groups) {
      for (IDisplayTrait trait: group.getTraits()) {
      if (trait.getValue() == 0) {
        continue;
      }
      if (!firstPrinted) {
        traitPhrase.add(createTextChunk(", ")); //$NON-NLS-1$
      }
      firstPrinted = false;
      if (trait.getFavorization().isFavored()) {
        traitPhrase.add(createTextChunk("*")); //$NON-NLS-1$
      }
      traitPhrase.add(AttributeMessages.get(trait.getTraitType().getId()));
      traitPhrase.add(createTextChunk(" " + String.valueOf(trait.getValue()))); //$NON-NLS-1$
    }
    }
    return traitPhrase;
  }
}