package net.sf.anathema.charms.character.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.charms.tree.ICharmId;

public class Combo {

  public String name;
  public String description;
  public final List<ICharmId> charms = new ArrayList<ICharmId>();
}