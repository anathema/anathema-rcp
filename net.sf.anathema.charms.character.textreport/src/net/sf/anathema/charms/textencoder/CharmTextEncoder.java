package net.sf.anathema.charms.textencoder;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.textreport.encoder.AbstractListTextEncoder;
import net.sf.anathema.charms.character.model.CharmCollector;
import net.sf.anathema.charms.data.lookup.CharmNamesExtensionPoint;
import net.sf.anathema.charms.tree.ICharmId;

import com.lowagie.text.Phrase;

public class CharmTextEncoder extends AbstractListTextEncoder<ICharmId> {

  @Override
  protected void print(Phrase listPhrase, ICharmId charm) {
    String name = new CharmNamesExtensionPoint().getNameFor(charm);
    listPhrase.add(createTextChunk(name));

  }

  @Override
  protected boolean isPrintable(ICharmId element) {
    return true;
  }
  
  @Override
  protected Iterable<ICharmId> getList(ICharacter character) {
    return new CharmCollector().collectLearnedCharms(character);
  }

  @Override
  protected String getTitle() {
    return "Charms:";
  }
}