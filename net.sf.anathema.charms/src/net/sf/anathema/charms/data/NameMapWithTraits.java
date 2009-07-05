package net.sf.anathema.charms.data;

import java.text.MessageFormat;

import net.sf.anathema.charms.traits.CharmTraits;
import net.sf.anathema.charms.traits.ICharmTraitMap;
import net.sf.anathema.charms.tree.ICharmId;

public class NameMapWithTraits implements INameMap {

  private final INameMap nameMap;
  private final ICharmTraitMap traitsMap;

  public NameMapWithTraits(INameMap nameMap, ICharmTraitMap traitsMap) {
    this.nameMap = nameMap;
    this.traitsMap = traitsMap;
  }

  @Override
  public String getNameFor(ICharmId charmId) {
    Object name = nameMap.getNameFor(charmId);
    CharmTraits traits = traitsMap.getTraits(charmId);
    return MessageFormat.format("{0} ({1}, {2})", name, traits.essenceMinimum, traits.primaryMinimum);
  }
}