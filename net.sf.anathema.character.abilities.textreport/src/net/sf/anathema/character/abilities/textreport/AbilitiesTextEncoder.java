package net.sf.anathema.character.abilities.textreport;

import net.sf.anathema.character.abilities.model.AbilitiesMessages;
import net.sf.anathema.character.abilities.util.AbilitiesDisplayGroupFactory;
import net.sf.anathema.character.trait.display.IDisplayGroupFactory;
import net.sf.anathema.character.trait.textreport.AbstractTraitCollectionTextEncoder;
import net.sf.anathema.lib.util.IIdentificate;

public class AbilitiesTextEncoder extends AbstractTraitCollectionTextEncoder {

  @Override
  protected String getTraitName(IIdentificate traitType) {
    return AbilitiesMessages.get(traitType.getId());
  }

  @Override
  protected IDisplayGroupFactory getFactory() {
    return new AbilitiesDisplayGroupFactory();
  }

  @Override
  protected String getTitle() {
    return Messages.AbilitiesTextEncoder_Title;
  }  
}