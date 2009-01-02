package net.sf.anathema.charms.character.selection;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.charms.tree.ITreeDtoMap;

public class ChooseTreePredicate implements IPredicate<String> {

  private final ICharacterType characterType;
  private final ITreeDtoMap dataMap;

  public ChooseTreePredicate(ICharacterType characterType, ITreeDtoMap dataMap) {
    this.characterType = characterType;
    this.dataMap = dataMap;
  }

  @Override
  public boolean evaluate(String treeId) {
    String treeType = dataMap.getData(treeId).characterType;
    return characterType.getId().endsWith(treeType);
  }
}