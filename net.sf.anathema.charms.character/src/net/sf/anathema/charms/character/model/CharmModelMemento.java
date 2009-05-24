package net.sf.anathema.charms.character.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.charms.tree.ICharmId;

public class CharmModelMemento {

  public final List<ICharmId> creationLearnedCharms = new ArrayList<ICharmId>();
  public final List<ICharmId> experienceLearnedCharms = new ArrayList<ICharmId>();
}