package net.sf.anathema.character.textreport.encoder;

import java.util.Collections;

import net.sf.anathema.character.core.character.ICharacter;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;

public abstract class AbstractListTextEncoder<T> extends AbstractTextEncoder {

  @Override
  public final Iterable<Element> createParagraphs(ICharacter character) throws DocumentException {
    Phrase listPhrase = new Phrase();
    for (T element : getList(character)) {
      if (!isPrintable(element)) {
        continue;
      }
      if (!listPhrase.isEmpty()) {
        listPhrase.add(createTextChunk(", ")); //$NON-NLS-1$
      }
      print(listPhrase, element);
    }
    return createParagraph(listPhrase);
  }

  protected abstract void print(Phrase listPhrase, T element);

  protected abstract Iterable<T> getList(ICharacter character);

  protected abstract boolean isPrintable(T element);

  protected final Iterable<Element> createParagraph(Phrase listPhrase) {
    if (listPhrase.isEmpty()) {
      return Collections.emptyList();
    }
    Phrase titleParagraph = createTextParagraph(createBoldTitle(getTitle()));
    titleParagraph.add(createBoldTitle(" ")); //$NON-NLS-1$
    titleParagraph.add(listPhrase);
    return Collections.singletonList((Element) titleParagraph);
  }

  protected abstract String getTitle();
}