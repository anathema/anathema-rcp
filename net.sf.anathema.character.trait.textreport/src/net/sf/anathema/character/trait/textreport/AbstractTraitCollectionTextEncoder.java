package net.sf.anathema.character.trait.textreport;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.textreport.encoder.AbstractListTextEncoder;
import net.sf.anathema.character.trait.display.IDisplayGroupFactory;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.lib.util.IIdentificate;

import com.lowagie.text.Phrase;

public abstract class AbstractTraitCollectionTextEncoder extends AbstractListTextEncoder<IDisplayTrait> {

  @Override
  protected void print(Phrase listPhrase, IDisplayTrait trait) {
    if (trait.getFavorization().getStatus().isCheap()) {
      listPhrase.add(createTextChunk("*")); //$NON-NLS-1$
    }
    listPhrase.add(createTextChunk(getTraitName(trait.getTraitType())));
    listPhrase.add(createTextChunk(" " + String.valueOf(trait.getValue()))); //$NON-NLS-1$ 
  }
  
  @Override
  protected boolean isPrintable(IDisplayTrait trait) {
    return trait.getValue() == 0;
  }

  @Override
  protected Iterable<IDisplayTrait> getList(ICharacter character) {
    ArrayList<IDisplayTrait> list = new ArrayList<IDisplayTrait>();
    for (IDisplayTraitGroup<IDisplayTrait> group : getFactory().createDisplayTraitGroups(character)) {
      for (IDisplayTrait trait : group.getTraits()) {
        list.add(trait);
      }
    }
    return list;
  }

  protected abstract IDisplayGroupFactory getFactory();

  protected abstract String getTraitName(IIdentificate traitType);
}