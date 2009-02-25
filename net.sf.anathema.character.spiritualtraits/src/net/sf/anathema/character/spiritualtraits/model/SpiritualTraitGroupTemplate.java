package net.sf.anathema.character.spiritualtraits.model;

import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;

public class SpiritualTraitGroupTemplate implements ITraitGroupTemplate {

  private static final String WILLPOWER_ID = "Willpower";
  private static final String VALOR_ID = "Valor"; //$NON-NLS-1$
  private static final String TEMPERANCE_ID = "Temperance"; //$NON-NLS-1$
  private static final String CONVICTION_ID = "Conviction"; //$NON-NLS-1$
  private static final String COMPASSION_ID = "Compassion"; //$NON-NLS-1$
  private static final String ESSENCE_GROUP_ID = "Essence"; //$NON-NLS-1$
  private static final String VIRTUES_GROUP_ID = "Virtues"; //$NON-NLS-1$
  private static final String WILLPOWER_GROUP_ID = WILLPOWER_ID;

  @Override
  public TraitGroup[] getGroups() {
    return new TraitGroup[] { createEssenceGroup(), createVirtuesGroup(), createWillpowerGroup() };
  }

  private TraitGroup createVirtuesGroup() {
    String label = Messages.SpiritualTraitGroupTemplate_VirtuesGroup;
    return new TraitGroup(VIRTUES_GROUP_ID, label, COMPASSION_ID, CONVICTION_ID, TEMPERANCE_ID, VALOR_ID);
  }

  private TraitGroup createEssenceGroup() {
    String label = Messages.SpiritualTraitGroupTemplate_EssenceGroup;
    return new TraitGroup(ESSENCE_GROUP_ID, label, IPluginConstants.ESSENCE_TRAIT_ID);
  }

  private TraitGroup createWillpowerGroup() {
    String label = Messages.SpiritualTraitGroupTemplate_WillpowerGroup;
    return new TraitGroup(WILLPOWER_GROUP_ID, label, WILLPOWER_ID);
  }
}