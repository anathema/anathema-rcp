package net.sf.anathema.charms.character.evaluation;

import java.util.Collection;
import java.util.List;

import net.sf.anathema.charms.tree.ICharmId;

public interface IGenericCharmCharacter {

  public List<ICharmId> getLearnedCharmIds(String genericId);

  public List<ICharmId> getAllCharmIds(String genericId);

  public Collection<String> getGenericIdPatterns(IGenericCharmTree charmTree);
}