package net.sf.anathema.character.spiritualtraits.model;

import static net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants.*;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;

public class SpiritualTraitGroupTemplate implements ITraitGroupTemplate {

  public static final String ESSENCE_GROUP_ID = "Essence"; //$NON-NLS-1$
  public static final String VIRTUES_GROUP_ID = "Virtues"; //$NON-NLS-1$
  public static final String WILLPOWER_GROUP_ID = "Willpower"; //$NON-NLS-1$

  @Override
  public TraitGroup[] getGroups() {
    return new TraitGroup[] { createEssenceGroup(), createVirtuesGroup(), createWillpowerGroup() };
  }

  public TraitGroup createVirtuesGroup() {
    final String label = Messages.SpiritualTraitGroupTemplate_VirtuesGroup;
    return new TraitGroup(VIRTUES_GROUP_ID, label, COMPASSION_ID, CONVICTION_ID, TEMPERANCE_ID, VALOR_ID);
  }

  private TraitGroup createEssenceGroup() {
    final String label = Messages.SpiritualTraitGroupTemplate_EssenceGroup;
    return new TraitGroup(ESSENCE_GROUP_ID, label, ESSENCE_TRAIT_ID);
  }

  private TraitGroup createWillpowerGroup() {
    final String label = Messages.SpiritualTraitGroupTemplate_WillpowerGroup;
    return new TraitGroup(WILLPOWER_GROUP_ID, label, WILLPOWER_ID);
  }
}