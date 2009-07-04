package net.sf.anathema.charms.character.print;

import static net.sf.anathema.charms.providing.CharmProvidingExtensionPoint.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.trait.resources.TraitMessages;
import net.sf.anathema.charms.character.evaluation.GenericCharmCharacter;
import net.sf.anathema.charms.character.evaluation.IGenericCharmCharacter;
import net.sf.anathema.charms.character.evaluation.IGenericCharmTree;
import net.sf.anathema.charms.character.evaluation.NonVirtualGenericCharmTree;
import net.sf.anathema.charms.character.special.IVirtualCharms;
import net.sf.anathema.charms.character.special.VirtualCharms;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.charms.tree.ITreeProvider;

public class GenericCharmCollector {

  private final IGenericCharmCharacter charmCharacter;
  private final IGenericCharmTree charmTree;

  public GenericCharmCollector(ICharacter character) {
    this(new VirtualCharms(), CreateTreeProvider(), character);
  }

  public GenericCharmCollector(IVirtualCharms virtualCharms, ITreeProvider treeProvider, ICharacter character) {
    this.charmCharacter = new GenericCharmCharacter(character);
    this.charmTree = new NonVirtualGenericCharmTree(treeProvider, virtualCharms);
  }

  public Collection<String> getRealGenericIdPatterns() {
    return charmCharacter.getGenericIdPatterns(charmTree);
  }

  public List<ICharmId> getLearnedGenerics() {
    Collection<String> allPatterns = getRealGenericIdPatterns();
    return getAllLearnedCharms(allPatterns);
  }

  private List<ICharmId> getAllLearnedCharms(Collection<String> allPatterns) {
    List<ICharmId> list = new ArrayList<ICharmId>();
    for (String genericId : allPatterns) {
      for (ICharmId charmId : charmCharacter.getLearnedCharmIds(genericId)) {
        list.add(charmId);
      }
    }
    return list;
  }

  public List<String> getLearnedTraits(String genericId) {
    List<String> traits = new ArrayList<String>();
    for (ICharmId charmId : charmCharacter.getLearnedCharmIds(genericId)) {
      traits.add(new TraitMessages().getNameFor(charmId.getPrimaryTrait()));
    }
    return traits;
  }
}