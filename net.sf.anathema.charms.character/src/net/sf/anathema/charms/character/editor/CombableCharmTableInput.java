package net.sf.anathema.charms.character.editor;

import java.util.Set;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.charms.character.editor.table.ICharmTableInput;
import net.sf.anathema.charms.character.evaluation.CharmCharacter;
import net.sf.anathema.charms.tree.ICharmId;

public final class CombableCharmTableInput implements ICharmTableInput {

  private final IModelContainer modelContainer;

  public CombableCharmTableInput(IModelContainer modelContainer) {
    this.modelContainer = modelContainer;
  }

  @Override
  public ICharmId[] getAllCharms() {
    CharmCharacter charmCharacter = new CharmCharacter(modelContainer);
    Set<ICharmId> allCharms = charmCharacter.getAllLearnedCharms();
    return allCharms.toArray(new ICharmId[allCharms.size()]);
  }
}