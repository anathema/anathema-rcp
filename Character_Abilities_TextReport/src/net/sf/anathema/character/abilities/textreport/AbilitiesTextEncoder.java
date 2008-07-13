package net.sf.anathema.character.abilities.textreport;

import java.util.List;

import net.sf.anathema.character.abilities.model.AbilitiesMessages;
import net.sf.anathema.character.abilities.util.AbilitiesDisplayUtilties;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.textreport.AbstractTraitCollectionTextEncoder;
import net.sf.anathema.lib.util.IIdentificate;

public class AbilitiesTextEncoder extends AbstractTraitCollectionTextEncoder {

  @Override
  protected String getTraitName(IIdentificate traitType) {
    return AbilitiesMessages.get(traitType.getId());
  }

  @Override
  protected List<IDisplayTraitGroup<IDisplayTrait>> getDisplayGroups(ICharacter character) {
    return AbilitiesDisplayUtilties.getDisplayAttributeGroups(character);
  }

  @Override
  protected String getTitle() {
    return Messages.AbilitiesTextEncoder_Title;
  }

  @Override
  public String getModelId() {
    return IAbilitiesPluginConstants.MODEL_ID;
  }
}