package net.sf.anathema.character.spiritualtraits.model;

import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;

public class SpiritualTraitGroupTemplate implements ITraitGroupTemplate {

  private static final String ESSENCE_TRAIT_ID = "Essence"; //$NON-NLS-1$
  private static final String ESSENCE_GROUP_ID = "Essence"; //$NON-NLS-1$

  @Override
  public TraitGroup[] getGroups() {
    return new TraitGroup[] { createEssenceGroup() };
  }

  private TraitGroup createEssenceGroup() {
    String label = Messages.SpiritualTraitGroupTemplate_EssenceGroup;
    return new TraitGroup(ESSENCE_GROUP_ID, label, ESSENCE_TRAIT_ID);
  }
}