package net.sf.anathema.charms.character.combo;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.lib.dto.ValueObject;

public class Combo extends ValueObject {

  public static Combo CreateFrom(Combo combo) {
    Combo newCombo = new Combo();
    newCombo.name = combo.name;
    newCombo.description = combo.description;
    newCombo.charms.addAll(combo.charms);
    return newCombo;
  }

  public String name;
  public String description;
  public final List<ICharmId> charms = new ArrayList<ICharmId>();
}