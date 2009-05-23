package net.sf.anathema.charms.textencoder;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.textreport.encoder.AbstractTextEncoder;
import net.sf.anathema.charms.character.model.GenericCharmCollector;
import net.sf.anathema.charms.data.lookup.CharmNamesExtensionPoint;
import net.sf.anathema.charms.tree.CharmId;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;

public class GenericCharmTextEncoder extends AbstractTextEncoder {

  @Override
  public Iterable<Element> createParagraphs(ICharacter character) throws DocumentException {
    GenericCharmCollector collector = new GenericCharmCollector(character);
    Collection<String> generics = collector.getUnvirtualGenericIdPatterns();
    CharmNamesExtensionPoint names = new CharmNamesExtensionPoint();
    Phrase genericsParagraph = createTextParagraph(createTextChunk("")); //$NON-NLS-1$
    for (String generic : generics) {
      List<String> traits = collector.getTraits(generic);
      if (traits.isEmpty()) {
        continue;
      }
      String charmName = getCharmName(names, generic);
      Chunk chunk = createChunk(character, traits, charmName);
      genericsParagraph.add(chunk);
      genericsParagraph.add(createTextChunk(".\n")); //$NON-NLS-1$
    }
    return Collections.singletonList((Element) genericsParagraph);
  }

  private Chunk createChunk(ICharacter character, List<String> traits, String charmName) {
    Chunk chunk = createTextChunk(MessageFormat.format(
        Messages.GenericCharmTextEncoder_Intro,
        character.getDisplayName(),
        charmName));
    for (String trait : traits) {
      int index = traits.indexOf(trait);
      if (index == traits.size() - 1 && index != 0) {
        chunk.append(Messages.GenericCharmTextEncoder_FinalConjunction);
      }
      else if (index != 0) {
        chunk.append(", "); //$NON-NLS-1$
      }
      chunk.append(trait);
    }
    return chunk;
  }

  private String getCharmName(CharmNamesExtensionPoint names, String generic) {
    String charmName = names.getNameFor(new CharmId(generic, "")); //$NON-NLS-1$
    return charmName.replaceAll("\\s+", " "); //$NON-NLS-1$ //$NON-NLS-2$
  }
}