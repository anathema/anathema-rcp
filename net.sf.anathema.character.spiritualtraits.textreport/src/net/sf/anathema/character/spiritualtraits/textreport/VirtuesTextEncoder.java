package net.sf.anathema.character.spiritualtraits.textreport;

import net.sf.anathema.character.spiritualtraits.display.SpiritualTraitDisplayGroupFactory;
import net.sf.anathema.character.spiritualtraits.display.SpiritualTraitLabelMap;
import net.sf.anathema.character.spiritualtraits.model.SpiritualTraitGroupTemplate;
import net.sf.anathema.character.spiritualtraits.model.SubTraitGroupFactory;
import net.sf.anathema.character.trait.display.IDisplayGroupFactory;
import net.sf.anathema.character.trait.textreport.AbstractTraitCollectionTextEncoder;
import net.sf.anathema.lib.util.IIdentificate;

public class VirtuesTextEncoder extends AbstractTraitCollectionTextEncoder {

  @Override
  protected IDisplayGroupFactory getFactory() {
    return new SubTraitGroupFactory(
        new SpiritualTraitDisplayGroupFactory(),
        SpiritualTraitGroupTemplate.VIRTUES_GROUP_ID);
  }

  @Override
  protected String getTraitName(final IIdentificate traitType) {
    return SpiritualTraitLabelMap.getLabel(traitType.getId());
  }

  @Override
  protected String getTitle() {
    return Messages.VirtuesTextEncoder_Title;
  }
}