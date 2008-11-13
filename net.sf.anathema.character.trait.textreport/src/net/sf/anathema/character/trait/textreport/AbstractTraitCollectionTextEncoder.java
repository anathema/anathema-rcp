package net.sf.anathema.character.trait.textreport;

import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.textreport.encoder.AbstractTextEncoder;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.lib.util.IIdentificate;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;

public abstract class AbstractTraitCollectionTextEncoder extends AbstractTextEncoder {

  public final Iterable<Element> createParagraphs(ICharacter character) throws DocumentException {
    Phrase traitPhrase = createTextParagraph(createBoldTitle(getTitle()));
    traitPhrase.add(createBoldTitle(" ")); //$NON-NLS-1$
    List<IDisplayTraitGroup<IDisplayTrait>> groups = getDisplayGroups(character);
    boolean firstPrinted = true;
    for (IDisplayTraitGroup<IDisplayTrait> group : groups) {
      for (IDisplayTrait trait : group.getTraits()) {
        if (trait.getValue() == 0) {
          continue;
        }
        if (!firstPrinted) {
          traitPhrase.add(createTextChunk(", ")); //$NON-NLS-1$
        }
        firstPrinted = false;
        if (trait.getFavorization().getStatus().isCheap()) {
          traitPhrase.add(createTextChunk("*")); //$NON-NLS-1$
        }
        traitPhrase.add(createTextChunk(getTraitName(trait.getTraitType())));
        traitPhrase.add(createTextChunk(" " + String.valueOf(trait.getValue()))); //$NON-NLS-1$
      }
    }
    return Collections.singletonList((Element) traitPhrase);
  }

  protected abstract String getTitle();

  protected abstract List<IDisplayTraitGroup<IDisplayTrait>> getDisplayGroups(ICharacter character);

  protected abstract String getTraitName(IIdentificate traitType);
}