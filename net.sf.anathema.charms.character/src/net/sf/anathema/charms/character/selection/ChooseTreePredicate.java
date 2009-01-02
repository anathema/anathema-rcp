package net.sf.anathema.charms.character.selection;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.charms.character.tree.ITreeCharacterDataMap;

public class ChooseTreePredicate implements IPredicate<String> {

  private final ICharacterType characterType;
  private final ITreeCharacterDataMap dataMap;

  public ChooseTreePredicate(ICharacterType characterType, ITreeCharacterDataMap dataMap) {
    this.characterType = characterType;
    this.dataMap = dataMap;
  }

  @Override
  public boolean evaluate(String treeId) {
    String treeType = dataMap.getCharacterType(treeId);
    return characterType.getId().endsWith(treeType);
  }
}