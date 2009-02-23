package net.sf.anathema.character.spiritualtraits.textreport;

import net.sf.anathema.character.spiritualtraits.display.SpiritualTraitDisplayGroupFactory;
import net.sf.anathema.character.trait.display.IDisplayGroupFactory;
import net.sf.anathema.character.trait.textreport.AbstractTraitCollectionTextEncoder;
import net.sf.anathema.lib.util.IIdentificate;

public class EssenceTextEncoder extends AbstractTraitCollectionTextEncoder {

  @Override
  protected IDisplayGroupFactory getFactory() {
    return new SpiritualTraitDisplayGroupFactory();
  }

  @Override
  protected String getTraitName(IIdentificate traitType) {
    return ""; //$NON-NLS-1$
  }

  @Override
  protected String getTitle() {
    return Messages.EssenceTextEncoder_Title;
  }
}